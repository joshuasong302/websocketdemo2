package com.bitgo.websocket.websocketdemo2.publisher

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic


class RedisMessagePublisher : MessagePublisher {

    @Autowired
    private var redisTemplate: RedisTemplate<String, Any>? = null

    @Autowired
    private var topic: ChannelTopic? = null

    constructor() {}

    constructor(
        redisTemplate: RedisTemplate<String, Any>?, topic: ChannelTopic?
    ) {
        this.redisTemplate = redisTemplate
        this.topic = topic
    }


    override fun publish(message: String?) {
        redisTemplate!!.convertAndSend(topic!!.topic, message!!)
    }
}