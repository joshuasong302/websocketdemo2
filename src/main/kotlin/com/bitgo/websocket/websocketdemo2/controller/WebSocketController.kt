package com.bitgo.websocket.websocketdemo2.controller

import com.bitgo.websocket.websocketdemo.entity.Greeting
import com.bitgo.websocket.websocketdemo.entity.HelloMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import com.bitgo.websocket.websocketdemo.entity.Message
import com.bitgo.websocket.websocketdemo.entity.OutputMessage
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import org.springframework.web.util.HtmlUtils
import java.text.SimpleDateFormat
import java.util.*


@Controller
class WebSocketController {

    @Autowired
    private val simpMessagingTemplate: SimpMessagingTemplate? = null

//    @MessageMapping("/chat")
//    @SendTo("/topic/messages")
//    @Throws(Exception::class)
//    fun send(message: Message): OutputMessage? {
//        val time = SimpleDateFormat("HH:mm").format(Date())
//        return OutputMessage(message.from, message.text, time)
//    }

    @MessageMapping("/chat")
    @Throws(java.lang.Exception::class)
    fun greeting(message: Message) {
        Thread.sleep(1000) // simulated delay
        var out = OutputMessage(message.text, SimpleDateFormat("HH:mm").format(Date()))
        simpMessagingTemplate?.convertAndSend("/topic/" + message.enId!!, out)
    }

//    @MessageMapping("/secured/room")
//    @Throws(Exception::class)
//    fun sendSpecific(
//        @Payload msg: Message,
//        user: Principal?,
//        @Header("simpSessionId") sessionId: String?
//    ) {
//        val out = OutputMessage(
//            msg.getFrom(),
//            msg.getText(),
//            SimpleDateFormat("HH:mm").format(Date())
//        )
//        simpMessagingTemplate.convertAndSendToUser(
//            msg.getTo(), "/secured/user/queue/specific-user", out
//        )
//    }

//    @GetMapping("/")
//    fun index(
//        model: Model
//    ): String? {
//        return "index"
//    }
}