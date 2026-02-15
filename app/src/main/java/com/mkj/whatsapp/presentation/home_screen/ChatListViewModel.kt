package com.mkj.whatsapp.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkj.whatsapp.domain.model.ChatListUiModel
import com.mkj.whatsapp.domain.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ChatListViewModel @Inject constructor(
    private val repository: ChatRepository
) : ViewModel() {

    // 👇 Replace with actual logged in user later
    private val currentUserId = "me"

    val chats: StateFlow<List<ChatListUiModel>> =
        repository.observeChats()
            .map { list ->
                list.map { item ->
                    ChatListUiModel(
                        chatId = item.chatId,
                        title = item.title ?: "Unknown",
                        isGroup = item.isGroup,
                        lastMessage = item.lastMessage,
                        lastMessageTime = item.lastMessageTime,
                        unreadCount = item.unreadCount
                    )
                }
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )
}
