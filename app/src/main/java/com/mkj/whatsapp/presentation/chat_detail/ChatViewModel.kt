package com.mkj.whatsapp.presentation.chat_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkj.whatsapp.data.repository.ChatRepository
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

    val chatUser: String =
        savedStateHandle["userName"] ?: ""

    val messages: StateFlow<List<ChatMessage>> =
        repository.observeMessages(chatUser)
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

    fun sendMessage(text: String) {
        viewModelScope.launch {
            repository.sendMessage(chatUser, text)
        }
    }
}
