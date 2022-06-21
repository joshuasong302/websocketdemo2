package com.bitgo.websocket.websocketdemo.entity

class HelloMessage {
    var message: String? = null

    constructor() {}
    constructor(name: String?) {
        this.message = name
    }
}