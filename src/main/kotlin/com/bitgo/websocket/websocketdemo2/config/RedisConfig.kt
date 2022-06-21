package com.bitgo.websocket.websocketdemo2.config

import com.bitgo.websocket.websocketdemo2.publisher.RedisMessagePublisher
import com.bitgo.websocket.websocketdemo2.publisher.MessagePublisher
import com.bitgo.websocket.websocketdemo2.subscriber.RedisMessageSubscriber
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer


@Configuration
@EnableWebSocketMessageBroker
class RedisConfig : WebSocketMessageBrokerConfigurer {

    @Bean
    fun jedisConnectionFactory(): JedisConnectionFactory {
        return JedisConnectionFactory()
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any>? {
        val template: RedisTemplate<String, Any> = RedisTemplate()
        template.setConnectionFactory(jedisConnectionFactory())
        return template
    }

    @Bean
    fun messageListener(): MessageListenerAdapter {
        return MessageListenerAdapter(RedisMessageSubscriber())
    }

    @Bean
    fun redisContainer(): RedisMessageListenerContainer? {
        val container = RedisMessageListenerContainer()
        container.setConnectionFactory(jedisConnectionFactory())
        container.addMessageListener(messageListener(), topic())
        return container
    }

    @Bean
    fun redisPublisher(): MessagePublisher {
        return RedisMessagePublisher(redisTemplate(), topic())
    }

    @Bean
    fun topic(): ChannelTopic {
        return ChannelTopic("messageQueue")
    }
}