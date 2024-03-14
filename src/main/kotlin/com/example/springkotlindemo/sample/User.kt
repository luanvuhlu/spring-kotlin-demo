package com.example.springkotlindemo.sample

interface User {
    val nickName: String
}

class PrivateUser(override val nickName: String): User

class SubscribingUser(private val email: String): User {
    override val nickName: String
        get() = email.substringBefore("@")
}

class FacebookUser(private val accountId: Int): User {
    override val nickName = getFacebookName(accountId)

    private fun getFacebookName(accountId: Int) = accountId.toString()
}