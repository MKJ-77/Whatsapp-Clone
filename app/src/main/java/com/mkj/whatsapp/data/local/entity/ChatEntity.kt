package com.mkj.whatsapp.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "chats",
    indices = [
        Index("lastMessageId"),
        Index("createdAt")
    ]
)
data class ChatEntity(
    @PrimaryKey val chatId: String,
    val isGroup: Boolean,
    val title: String?,
    val createdAt: Long,
    val lastMessageId: String?
)
