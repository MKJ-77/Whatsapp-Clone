package com.mkj.whatsapp.data.repository

import com.mkj.whatsapp.data.local.SessionManager
import com.mkj.whatsapp.data.local.dao.UserDao
import com.mkj.whatsapp.data.local.entity.UserEntity
import com.mkj.whatsapp.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val sessionManager: SessionManager,
    private val userDao: UserDao
) : AuthRepository {

    override suspend fun sendOtp(phone: String) {
        delay(1000) // simulate API call
    }

    override suspend fun verifyOtp(
        phone: String,
        otp: String
    ): String {

        // Simulate backend success
        val userId = UUID.randomUUID().toString()

        val user = UserEntity(
            userId = userId,
            name = phone,
            phone = phone,
            profilePicture = null,
            about = null,
            lastSeen = System.currentTimeMillis(),
            isOnline = true
        )

        userDao.insertUser(user)

        sessionManager.saveUser(userId)

        return userId
    }

    override fun observeLoggedInUser() =
        sessionManager.getUser()

    override suspend fun logout() {
        sessionManager.logout()
    }
}
