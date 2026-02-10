package com.mkj.whatsapp.data.local.db

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    fun provideDatabase(context: Context): ChatDatabase =
        Room.databaseBuilder(
            context,
            ChatDatabase::class.java,
            "chat_db"
        ).build()
}
