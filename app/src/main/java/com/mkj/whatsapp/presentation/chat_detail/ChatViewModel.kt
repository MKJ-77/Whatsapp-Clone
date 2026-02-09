package com.mkj.whatsapp.presentation.chat_detail

import androidx.lifecycle.ViewModel
import com.mkj.whatsapp.model.ChatMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChatViewModel : ViewModel() {

    private val _messages = MutableStateFlow<List<ChatMessage>>(
        listOf(
            ChatMessage("1", "Hey!", false, "9:00"),
            ChatMessage("2", "Hi 👋", true, "9:01"),
            ChatMessage("3", "How are you?", false, "9:02"),
            ChatMessage("4", "Doing great 😄", true, "9:03")
        )
    )

    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()

    fun sendMessage(text: String) {
        val newMessage = ChatMessage(
            id = System.currentTimeMillis().toString(),
            text = text,
            isMine = true,
            time = "Now"
        )

        _messages.value = _messages.value + newMessage
    }
}
