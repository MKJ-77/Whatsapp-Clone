package com.mkj.whatsapp.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.mkj.whatsapp.domain.model.CallDirection
import com.mkj.whatsapp.domain.model.CallMediaType
import com.mkj.whatsapp.domain.model.CallStatus

@Entity(
    tableName = "calls",
    indices = [
        Index("chatId"),
        Index("timestamp")
    ]
)
data class CallEntity(
    @PrimaryKey val callId: String,
    val chatId: String,
    val callerId: String,
    val timestamp: Long,
    val duration: Long,
    val mediaType: CallMediaType,
    val direction: CallDirection,
    val status: CallStatus
)

