package com.mkj.whatsapp.data.repository

import com.mkj.whatsapp.data.local.dao.MessageDao
import com.mkj.whatsapp.data.local.entity.MessageEntity
import kotlinx.coroutines.flow.Flow

class ChatRepository(
    private val dao: MessageDao
) {

    fun getMessages(user: String): Flow<List<MessageEntity>> =
        dao.getMessages(user)

    suspend fun sendMessage(message: MessageEntity) {
        dao.insertMessage(message)
    }
}
