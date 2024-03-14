package com.example.springkotlindemo.service

import com.example.springkotlindemo.entity.Message
import com.example.springkotlindemo.repository.MessageRepository
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class MessageService (private val repo: MessageRepository){

    fun findById(id: Int) : Message =
        repo.findById(id).orElse(null) ?: throw EntityNotFoundException("Message id $id not found")

    fun save(message: Message): Message = repo.save(message)
    fun findAll(): List<Message> = repo.findAll()
}