package com.mkj.whatsapp.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class MessageWithSender(
    @Embedded val message: MessageEntity,

    @Relation(
        parentColumn = "senderId",
        entityColumn = "userId"
    )
    val sender: UserEntity
)
