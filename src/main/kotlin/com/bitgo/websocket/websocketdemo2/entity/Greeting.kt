package com.bitgo.websocket.websocketdemo.entity

class Greeting {
    var content: String? = null

    constructor() {}
    constructor(content: String?) {
        this.content = content
    }
}