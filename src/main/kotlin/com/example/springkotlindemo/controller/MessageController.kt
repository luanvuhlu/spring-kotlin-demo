package com.example.springkotlindemo.controller

import com.example.springkotlindemo.entity.Message
import com.example.springkotlindemo.service.MessageService
import org.springframework.web.bind.annotation.*

@RestController
class MessageController (private val service: MessageService) {

    @PostMapping("/message")
    fun insert(@RequestBody message: Message): Message {
        val newMessage = message.apply { id = null }
        return service.save(newMessage)
    }

    @PutMapping("/message/{id}")
    fun update(@PathVariable id: Int, @RequestBody message: Message): Message {
        val oldMessage = service.findById(id)
        oldMessage.text = message.text
        return service.save(message)
    }

    @GetMapping("/message/{id}")
    fun getMessage(@PathVariable id: Int): Message = service.findById(id)

    @GetMapping("/messages")
    fun getMessages(): List<Message> = service.findAll()
}