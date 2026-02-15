package com.mkj.whatsapp.di

import android.content.Context
import androidx.room.Room
import com.mkj.whatsapp.data.local.dao.CallDao
import com.mkj.whatsapp.data.local.dao.ChatDao
import com.mkj.whatsapp.data.local.dao.MessageDao
import com.mkj.whatsapp.data.local.dao.UserDao
import com.mkj.whatsapp.data.local.db.ChatDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): ChatDatabase =
        Room.databaseBuilder(
            context,
            ChatDatabase::class.java,
            "chat_db"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideUserDao(
        database: ChatDatabase
    ): UserDao = database.userDao()

    @Provides
    fun provideChatDao(
        database: ChatDatabase
    ): ChatDao = database.chatDao()

    @Provides
    fun provideMessageDao(
        database: ChatDatabase
    ): MessageDao = database.messageDao()

    @Provides
    fun provideCallDao(
        database: ChatDatabase
    ): CallDao = database.callDao()
}
