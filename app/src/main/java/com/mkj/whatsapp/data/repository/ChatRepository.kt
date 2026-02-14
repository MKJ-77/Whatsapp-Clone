package com.mkj.whatsapp.data.repository

import com.mkj.whatsapp.data.local.dao.MessageDao
import com.mkj.whatsapp.data.local.entity.MessageEntity
import com.mkj.whatsapp.data.remote.WebSocketManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepository @Inject constructor(
    private val dao: MessageDao,
    private val socketManager: WebSocketManager
) {

    private val repositoryScope = CoroutineScope(Dispatchers.IO)

    init {
        socketManager.setListener { incomingText ->
            repositoryScope.launch {
                // Example parsing (adjust to your backend format)
                val parts = incomingText.split("|")
                if (parts.size == 2) {
                    val user = parts[0]
                    val message = parts[1]

                    dao.insertMessage(
                        MessageEntity(
                            id = System.currentTimeMillis().toString(),
                            chatUser = user,
                            text = message,
                            isMine = false,
                            time = currentTime()
                        )
                    )
                }
            }
        }

        socketManager.connectIfNeeded()
    }

    fun observeMessages(user: String): Flow<List<MessageEntity>> {
        return dao.getMessages(user)
    }

    suspend fun sendMessage(chatUser: String, text: String) {
        // Save locally first
        dao.insertMessage(
            MessageEntity(
                id = System.currentTimeMillis().toString(),
                chatUser = chatUser,
                text = text,
                isMine = true,
                time = currentTime()
            )
        )

        // Send to server
        socketManager.send("$chatUser|$text")
    }

    private fun currentTime(): String {
        return java.text.SimpleDateFormat(
            "HH:mm",
            java.util.Locale.getDefault()
        ).format(java.util.Date())
    }
}
