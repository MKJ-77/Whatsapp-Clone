package com.mkj.whatsapp.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkj.whatsapp.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    val loggedInUser = repository.observeLoggedInUser()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            null
        )

    fun sendOtp(phone: String) {
        viewModelScope.launch {
            repository.sendOtp(phone)
        }
    }

    fun verifyOtp(
        phone: String,
        otp: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            repository.verifyOtp(phone, otp)
            onSuccess()
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}
