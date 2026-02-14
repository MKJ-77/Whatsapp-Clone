package com.mkj.whatsapp.data.remote

import okhttp3.*
import okio.ByteString
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebSocketManager @Inject constructor() {

    private val client = OkHttpClient()
    private var webSocket: WebSocket? = null
    private var listener: ((String) -> Unit)? = null
    private var isConnected = false

    fun setListener(onMessage: (String) -> Unit) {
        listener = onMessage
    }

    fun connectIfNeeded() {
        if (isConnected) return

        val request = Request.Builder()
            .url("ws://10.0.2.2:8080/chat")
            .build()

        webSocket = client.newWebSocket(
            request,
            object : WebSocketListener() {

                override fun onOpen(webSocket: WebSocket, response: Response) {
                    isConnected = true
                    println("WebSocket Connected")
                }

                override fun onMessage(webSocket: WebSocket, text: String) {
                    listener?.invoke(text)
                }

                override fun onMessage(webSocket: WebSocket, bytes: ByteString) {}

                override fun onFailure(
                    webSocket: WebSocket,
                    t: Throwable,
                    response: Response?
                ) {
                    isConnected = false
                    t.printStackTrace()
                }

                override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                    isConnected = false
                }
            }
        )
    }

    fun send(message: String) {
        webSocket?.send(message)
    }

    fun disconnect() {
        webSocket?.close(1000, "Closed")
        isConnected = false
    }
}
