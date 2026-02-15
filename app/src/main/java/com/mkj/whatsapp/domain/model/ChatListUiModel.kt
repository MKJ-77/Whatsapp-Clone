package com.mkj.whatsapp.domain.model

data class ChatListUiModel(
    val chatId: String,
    val title: String,
    val isGroup: Boolean,
    val lastMessage: String?,
    val lastMessageTime: Long?,
    val unreadCount: Int
)
