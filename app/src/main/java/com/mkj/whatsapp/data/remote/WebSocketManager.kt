package com.mkj.whatsapp.data.remote

import okhttp3.*
import okio.ByteString
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebSocketManager @Inject constructor() {

    private val client = OkHttpClient()
    private var webSocket: WebSocket? = null
    private var isConnected = false
    private var listener: ((SocketMessage) -> Unit)? = null

    fun setListener(onMessage: (SocketMessage) -> Unit) {
        listener = onMessage
    }

    fun connect() {
        if (isConnected) return

        val request = Request.Builder()
            .url("ws://10.0.2.2:8080/chat")
            .build()

        webSocket = client.newWebSocket(
            request,
            object : WebSocketListener() {

                override fun onOpen(ws: WebSocket, response: Response) {
                    isConnected = true
                }

                override fun onMessage(ws: WebSocket, text: String) {
                    val message = parseMessage(text)
                    listener?.invoke(message)
                }

                override fun onFailure(
                    ws: WebSocket,
                    t: Throwable,
                    response: Response?
                ) {
                    isConnected = false
                }

                override fun onClosed(
                    ws: WebSocket,
                    code: Int,
                    reason: String
                ) {
                    isConnected = false
                }
            }
        )
    }

    fun send(message: SocketMessage) {
        webSocket?.send(message.toJson())
    }

    fun disconnect() {
        webSocket?.close(1000, null)
        isConnected = false
    }

    private fun parseMessage(text: String): SocketMessage {
        // TODO: Use proper JSON parsing later
        return SocketMessage.fromJson(text)
    }
}
