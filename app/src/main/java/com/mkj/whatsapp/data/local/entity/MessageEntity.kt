package com.mkj.whatsapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey val id: String,
    val chatUser: String,
    val text: String,
    val isMine: Boolean,
    val time: String
)
