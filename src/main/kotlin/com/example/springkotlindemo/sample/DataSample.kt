package com.example.springkotlindemo.sample

data class DataSample(val name: String, val client: Client)

class Client(val name: String)

fun main() {
    val data = DataSample("Data", Client("client"))
    val dataClone = data.copy()
    println(dataClone)
    print(data.client === dataClone.client)
}