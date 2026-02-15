package com.mkj.whatsapp.domain.usecase

import com.mkj.whatsapp.domain.repository.ChatRepository

class SendMessageUseCase(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(chatId: String, text: String) {
        repository.sendMessage(chatId, text)
    }
}
