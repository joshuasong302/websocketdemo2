package com.bitgo.websocket.websocketdemo.entity

import org.springframework.data.redis.core.RedisHash
import java.io.Serializable

@RedisHash("Request")
class Request : Serializable{

    enum class Status {
        PENDING, COMPLETE
    }

    var id: String? = null
    var name: String? = null
    var status: Request.Status? = null
}
