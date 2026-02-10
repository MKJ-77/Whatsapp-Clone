package com.mkj.whatsapp.data.repository

import com.mkj.whatsapp.data.local.dao.MessageDao
import com.mkj.whatsapp.data.local.entity.MessageEntity
import com.mkj.whatsapp.data.remote.WebSocketManager
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ChatRepository @Inject constructor(
    private val dao: MessageDao
) {

    private var socketManager: WebSocketManager? = null

    fun observeMessages(user: String) =
        dao.getMessages(user)

    fun connectSocket(onIncoming: (String) -> Unit) {
        socketManager = WebSocketManager(onIncoming)
        socketManager?.connect()
    }

    fun sendRealtimeMessage(text: String) {
        socketManager?.send(text)
    }

    suspend fun saveMessage(message: MessageEntity) {
        dao.insertMessage(message)
    }
}
