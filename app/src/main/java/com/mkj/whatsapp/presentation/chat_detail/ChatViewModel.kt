package com.mkj.whatsapp.presentation.chat_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkj.whatsapp.data.local.entity.MessageEntity
import com.mkj.whatsapp.domain.repository.ChatRepository
import com.mkj.whatsapp.model.ChatMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repository: ChatRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val chatId: String =
        savedStateHandle["userName"] ?: ""

    val messages: StateFlow<List<ChatMessage>> =
        repository.observeMessages(chatId)
            .map { list ->
                list.map {
                    ChatMessage(
                        id = it.messageId,
                        text = it.content ?: "",
                        isMine = it.senderId == "me",
                        time = formatTime(it.timestamp)
                    )
                }
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )

    init {
        repository.connect()
    }

    fun sendMessage(text: String) {
        viewModelScope.launch {
            repository.sendMessage(chatId, text)
        }
    }

    override fun onCleared() {
        repository.disconnect()
        super.onCleared()
    }

    private fun formatTime(timestamp: Long): String {
        return java.text.SimpleDateFormat(
            "HH:mm",
            java.util.Locale.getDefault()
        ).format(java.util.Date(timestamp))
    }
}
