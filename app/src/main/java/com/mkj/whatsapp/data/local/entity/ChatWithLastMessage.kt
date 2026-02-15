package com.mkj.whatsapp.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ChatWithLastMessage(
    @Embedded val chat: ChatEntity,

    @Relation(
        parentColumn = "lastMessageId",
        entityColumn = "messageId"
    )
    val lastMessage: MessageEntity?
)
