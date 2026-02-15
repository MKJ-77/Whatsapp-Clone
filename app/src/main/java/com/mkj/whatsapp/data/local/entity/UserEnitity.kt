package com.mkj.whatsapp.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "users",
    indices = [Index("phone", unique = true)]
)

data class UserEntity(
    @PrimaryKey val userId: String,
    val name: String,
    val phone: String,
    val profilePicture: String?,
    val about: String?,
    val lastSeen: Long,
    val isOnline: Boolean
)
