package com.example.springkotlindemo.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Message (
    @Id @GeneratedValue var id: Int? = null,
    @Column var text: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Message

        if (id != other.id) return false
        if (text != other.text) return false

        return true
    }

    override fun hashCode() = 13
}