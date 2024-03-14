package com.example.springkotlindemo.repository

import com.example.springkotlindemo.entity.Message
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class MessageRepositoryTest @Autowired constructor(val repository: MessageRepository){

    @Test
    fun `When findById then return message`() {
        val message = Message(text = "Hello World")
        val newMessage = repository.save(message)
        val findMessage = repository.findById(newMessage.id!!).orElse(null)
        assertNotNull(findMessage)
        assertThat(findMessage).isEqualTo(newMessage)
    }
}