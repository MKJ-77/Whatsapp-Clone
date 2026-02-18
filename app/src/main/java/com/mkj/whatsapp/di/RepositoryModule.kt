package com.mkj.whatsapp.di

import com.mkj.whatsapp.data.repository.AuthRepositoryImpl
import com.mkj.whatsapp.data.repository.ChatRepositoryImpl
import com.mkj.whatsapp.domain.repository.AuthRepository
import com.mkj.whatsapp.domain.repository.ChatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindChatRepository(
        impl: ChatRepositoryImpl
    ): ChatRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository
}
