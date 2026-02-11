package com.mkj.whatsapp.data.remote

import okhttp3.*
import okio.ByteString

class WebSocketManager(
    private val onMessageReceived: (String) -> Unit
) {

    private val client = OkHttpClient()
    private var webSocket: WebSocket? = null

    fun connect() {
        val request = Request.Builder()
            .url("ws://10.0.2.2:8080/chat") // emulator → localhost
            .build()

        webSocket = client.newWebSocket(
            request,
            object : WebSocketListener() {

                override fun onOpen(webSocket: WebSocket, response: Response) {
                    println("WebSocket connected")
                }

                override fun onMessage(webSocket: WebSocket, text: String) {
                    onMessageReceived(text)
                }

                override fun onMessage(webSocket: WebSocket, bytes: ByteString) {}

                override fun onFailure(
                    webSocket: WebSocket,
                    t: Throwable,
                    response: Response?
                ) {
                    t.printStackTrace()
                }
            }
        )
    }

    fun send(message: String) {
        webSocket?.send(message)
    }

    fun disconnect() {
        webSocket?.close(1000, "Closed")
    }
}
