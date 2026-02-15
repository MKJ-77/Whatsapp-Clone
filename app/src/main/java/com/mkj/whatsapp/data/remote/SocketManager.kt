package com.mkj.whatsapp.data.remote
data class SocketMessage(
    val messageId: String,
    val chatId: String,
    val senderId: String,
    val content: String,
    val timestamp: Long
) {
    fun toJson(): String {
        return "$messageId|$chatId|$senderId|$content|$timestamp"
    }

    companion object {
        fun fromJson(text: String): SocketMessage {
            val parts = text.split("|")
            return SocketMessage(
                messageId = parts[0],
                chatId = parts[1],
                senderId = parts[2],
                content = parts[3],
                timestamp = parts[4].toLong()
            )
        }
    }
}
