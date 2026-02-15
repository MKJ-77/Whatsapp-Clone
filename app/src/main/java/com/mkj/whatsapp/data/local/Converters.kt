package com.mkj.whatsapp.data.local

import androidx.room.TypeConverter
import com.mkj.whatsapp.data.local.entity.MessageStatus
import com.mkj.whatsapp.data.local.entity.MessageType
import com.mkj.whatsapp.domain.model.*

class Converters {

    // -----------------------------
    // MessageType
    // -----------------------------
    @TypeConverter
    fun fromMessageType(type: MessageType): String = type.name

    @TypeConverter
    fun toMessageType(value: String): MessageType =
        MessageType.valueOf(value)

    // -----------------------------
    // MessageStatus
    // -----------------------------
    @TypeConverter
    fun fromMessageStatus(status: MessageStatus): String =
        status.name

    @TypeConverter
    fun toMessageStatus(value: String): MessageStatus =
        MessageStatus.valueOf(value)

    // -----------------------------
    // ParticipantRole
    // -----------------------------
    @TypeConverter
    fun fromParticipantRole(role: ParticipantRole): String =
        role.name

    @TypeConverter
    fun toParticipantRole(role: String): ParticipantRole =
        ParticipantRole.valueOf(role)

    // -----------------------------
    // CallMediaType
    // -----------------------------
    @TypeConverter
    fun fromCallMediaType(type: CallMediaType): String =
        type.name

    @TypeConverter
    fun toCallMediaType(value: String): CallMediaType =
        CallMediaType.valueOf(value)

    // -----------------------------
    // CallDirection
    // -----------------------------
    @TypeConverter
    fun fromCallDirection(direction: CallDirection): String =
        direction.name

    @TypeConverter
    fun toCallDirection(value: String): CallDirection =
        CallDirection.valueOf(value)

    // -----------------------------
    // CallStatus
    // -----------------------------
    @TypeConverter
    fun fromCallStatus(status: CallStatus): String =
        status.name

    @TypeConverter
    fun toCallStatus(value: String): CallStatus =
        CallStatus.valueOf(value)
}
