package com.example.springkotlindemo.repository

import com.example.springkotlindemo.entity.Message
import org.springframework.data.jpa.repository.JpaRepository

interface MessageRepository : JpaRepository<Message, Int> {

}