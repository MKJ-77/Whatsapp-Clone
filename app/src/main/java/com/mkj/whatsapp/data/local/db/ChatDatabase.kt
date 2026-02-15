package com.mkj.whatsapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mkj.whatsapp.data.local.Converters
import com.mkj.whatsapp.data.local.dao.CallDao
import com.mkj.whatsapp.data.local.dao.ChatDao
import com.mkj.whatsapp.data.local.dao.MessageDao
import com.mkj.whatsapp.data.local.dao.UserDao
import com.mkj.whatsapp.data.local.entity.CallEntity
import com.mkj.whatsapp.data.local.entity.ChatEntity
import com.mkj.whatsapp.data.local.entity.ChatParticipantEntity
import com.mkj.whatsapp.data.local.entity.MessageEntity
import com.mkj.whatsapp.data.local.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        ChatEntity::class,
        ChatParticipantEntity::class,
        MessageEntity::class,
        CallEntity::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ChatDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun chatDao(): ChatDao
    abstract fun messageDao(): MessageDao
    abstract fun callDao(): CallDao
}
