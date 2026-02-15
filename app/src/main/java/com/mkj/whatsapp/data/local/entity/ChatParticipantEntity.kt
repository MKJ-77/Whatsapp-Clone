package com.mkj.whatsapp.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import com.mkj.whatsapp.domain.model.ParticipantRole

@Entity(
    tableName = "chat_participants",
    primaryKeys = ["chatId", "userId"],
    indices = [Index("userId")]
)
data class ChatParticipantEntity(
    val chatId: String,
    val userId: String,
    val role: ParticipantRole
)
