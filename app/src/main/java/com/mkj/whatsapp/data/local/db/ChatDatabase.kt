package com.mkj.whatsapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mkj.whatsapp.data.local.dao.MessageDao
import com.mkj.whatsapp.data.local.entity.MessageEntity

@Database(
    entities = [MessageEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ChatDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
}
