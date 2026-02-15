package com.mkj.whatsapp.domain.repository

import com.mkj.whatsapp.data.local.entity.MessageEntity
import com.mkj.whatsapp.data.local.model.ChatListItem
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    // Observe chat list
    fun observeChats(): Flow<List<ChatListItem>>

    // Observe messages inside a chat
    fun observeMessages(chatId: String): Flow<List<MessageEntity>>

    // Send a new message
    suspend fun sendMessage(chatId: String, content: String)

    // Update message status (DELIVERED / SEEN etc.)
    suspend fun updateMessageStatus(
        messageId: String,
        status: String
    )

    // Realtime socket
    fun connect()
    fun disconnect()
}
