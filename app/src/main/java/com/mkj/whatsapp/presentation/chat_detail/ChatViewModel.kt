package com.mkj.whatsapp.presentation.chat_detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mkj.whatsapp.data.local.db.DatabaseProvider
import com.mkj.whatsapp.data.local.entity.MessageEntity
import com.mkj.whatsapp.data.repository.ChatRepository
import com.mkj.whatsapp.model.ChatMessage
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ChatViewModel(
    application: Application,
    private val chatUser: String
) : AndroidViewModel(application) {

    private val repository: ChatRepository

    val messages: StateFlow<List<ChatMessage>>

    init {
        val db = DatabaseProvider.provideDatabase(application)
        repository = ChatRepository(db.messageDao())

        messages = repository.getMessages(chatUser)
            .map { list ->
                list.map {
                    ChatMessage(
                        id = it.id,
                        text = it.text,
                        isMine = it.isMine,
                        time = it.time
                    )
                }
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                emptyList()
            )
    }

    fun sendMessage(text: String) {
        viewModelScope.launch {
            repository.sendMessage(
                MessageEntity(
                    id = System.currentTimeMillis().toString(),
                    chatUser = chatUser,
                    text = text,
                    isMine = true,
                    time = "Now"
                )
            )
        }
    }
}
