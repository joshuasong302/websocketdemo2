package com.bitgo.websocket.websocketdemo2.publisher

interface MessagePublisher {
    fun publish(message: String?)
}