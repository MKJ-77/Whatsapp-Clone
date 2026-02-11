package com.mkj.whatsapp.model

data class ChatMessage(
    val id: String,
    val text: String,
    val isMine: Boolean,
    val time: String
)
