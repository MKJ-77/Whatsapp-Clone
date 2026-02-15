package com.mkj.whatsapp.data.repository

import com.mkj.whatsapp.data.local.dao.ChatDao
import com.mkj.whatsapp.data.local.dao.MessageDao
import com.mkj.whatsapp.data.local.entity.MessageEntity
import com.mkj.whatsapp.data.local.entity.MessageStatus
import com.mkj.whatsapp.data.local.entity.MessageType
import com.mkj.whatsapp.data.local.model.ChatListItem
import com.mkj.whatsapp.data.remote.SocketMessage
import com.mkj.whatsapp.data.remote.WebSocketManager
import com.mkj.whatsapp.domain.repository.ChatRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepositoryImpl @Inject constructor(
    private val chatDao: ChatDao,
    private val messageDao: MessageDao,
    private val socketManager: WebSocketManager
) : ChatRepository {

    private val repositoryScope = CoroutineScope(Dispatchers.IO)

    /* -------------------------------------------------- */
    /* ---------------- OBSERVE SECTION ----------------- */
    /* -------------------------------------------------- */


    override fun observeChats(): Flow<List<ChatListItem>> {
        return chatDao.observeChatList(currentUserId = "me")
    }

    override fun observeMessages(chatId: String): Flow<List<MessageEntity>> {
        return messageDao.observeMessages(chatId)
    }

    /* -------------------------------------------------- */
    /* ---------------- SEND MESSAGE -------------------- */
    /* -------------------------------------------------- */

    override suspend fun sendMessage(
        chatId: String,
        content: String
    ) {

        val messageId = UUID.randomUUID().toString()
        val timestamp = System.currentTimeMillis()

        val message = MessageEntity(
            messageId = messageId,
            chatId = chatId,
            senderId = "me",
            type = MessageType.TEXT,
            content = content,
            mediaUrl = null,
            timestamp = timestamp,
            status = MessageStatus.SENDING,
            isSynced = false,
            serverId = null,
            readAt = null
        )

        // 1️⃣ Save locally
        messageDao.insertMessage(message)

        // 2️⃣ Update chat lastMessage
        chatDao.updateLastMessage(chatId, messageId)

        // 3️⃣ Send to socket
        val socketMessage = SocketMessage(
            messageId = messageId,
            chatId = chatId,
            senderId = "me",
            content = content,
            timestamp = timestamp
        )

        socketManager.send(socketMessage)

        // 4️⃣ Mark as SENT
        messageDao.updateStatus(
            messageId,
            MessageStatus.SENT
        )
    }

    /* -------------------------------------------------- */
    /* ---------------- UPDATE STATUS ------------------- */
    /* -------------------------------------------------- */

    override suspend fun updateMessageStatus(
        messageId: String,
        status: String
    ) {
        messageDao.updateStatus(
            messageId,
            MessageStatus.valueOf(status)
        )
    }

    /* -------------------------------------------------- */
    /* ---------------- SOCKET CONNECT ------------------ */
    /* -------------------------------------------------- */

    override fun connect() {

        socketManager.setListener { socketMessage ->

            repositoryScope.launch {

                val incomingMessage = MessageEntity(
                    messageId = socketMessage.messageId,
                    chatId = socketMessage.chatId,
                    senderId = socketMessage.senderId,
                    type = MessageType.TEXT,
                    content = socketMessage.content,
                    mediaUrl = null,
                    timestamp = socketMessage.timestamp,
                    status = MessageStatus.DELIVERED,
                    isSynced = false,
                    serverId = null,
                    readAt = null
                )

                messageDao.insertMessage(incomingMessage)

                chatDao.updateLastMessage(
                    chatId = socketMessage.chatId,
                    messageId = socketMessage.messageId
                )
            }
        }

        socketManager.connect()
    }

    override fun disconnect() {
        socketManager.disconnect()
    }
}
