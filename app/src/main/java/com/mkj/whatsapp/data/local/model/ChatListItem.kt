package com.mkj.whatsapp.data.local.model

data class ChatListItem(
    val chatId: String,
    val title: String?,
    val isGroup: Boolean,
    val lastMessage: String?,
    val lastMessageTime: Long?,
    val unreadCount: Int
)
