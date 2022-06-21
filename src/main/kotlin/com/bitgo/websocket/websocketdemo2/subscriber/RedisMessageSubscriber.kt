package com.bitgo.websocket.websocketdemo2.subscriber

import org.springframework.data.redis.connection.MessageListener
import org.springframework.data.redis.connection.Message;
import org.springframework.stereotype.Service


@Service
class RedisMessageSubscriber : MessageListener {
    override fun onMessage(message: Message, pattern: ByteArray?) {
        messageList.add(message.toString())
        System.out.println("Message received: " + message.toString())
    }

    companion object {
        var messageList: MutableList<String> = ArrayList()
    }
}