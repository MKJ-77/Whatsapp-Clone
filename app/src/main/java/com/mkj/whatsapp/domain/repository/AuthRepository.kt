package com.mkj.whatsapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun sendOtp(phone: String)

    suspend fun verifyOtp(
        phone: String,
        otp: String
    ): String // return userId

    fun observeLoggedInUser(): Flow<String?>

    suspend fun logout()
}