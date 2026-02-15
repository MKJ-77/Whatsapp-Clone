package com.mkj.whatsapp.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.mkj.whatsapp.data.local.entity.MessageStatus
import com.mkj.whatsapp.data.local.entity.MessageType

@Entity(
    tableName = "messages",
    indices = [Index("chatId"), Index("senderId")]
)
data class MessageEntity(
    @PrimaryKey val messageId: String,
    val chatId: String,
    val senderId: String,
    val type: MessageType,
    val content: String?,
    val mediaUrl: String?,
    val timestamp: Long,
    val status: MessageStatus,
    val isSynced: Boolean,
    val serverId: String?,
    val readAt: Long?

)
