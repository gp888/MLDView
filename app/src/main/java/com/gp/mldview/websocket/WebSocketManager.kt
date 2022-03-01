package com.gp.mldview.websocket

import okhttp3.*
import java.util.concurrent.TimeUnit

object WebSocketManager {
    private const val WS_URL = "ws://x.x.x"
    private val httpClient by lazy {
        OkHttpClient().newBuilder()
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .pingInterval(40, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }
    private var webSocket: WebSocket? = null

    private fun connect() {
        val request = Request.Builder()
            .url(WS_URL)
            .build()
        httpClient.newWebSocket(request, wsListener)
    }

    private val wsListener = object : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {
            super.onOpen(webSocket, response)
            // 连接建立
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
            // 收到服务端发送来的 String 类型消息
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosing(webSocket, code, reason)
            // 收到服务端发来的 CLOSE 帧消息，准备关闭连接
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosed(webSocket, code, reason)
            // 连接关闭
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            super.onFailure(webSocket, t, response)
            // 出错了
        }
    }
}