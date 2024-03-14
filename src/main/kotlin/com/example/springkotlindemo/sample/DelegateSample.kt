package com.example.springkotlindemo.sample

class DelegateCollection<T>(private val innerList: MutableList<T>) : MutableList<T> by innerList

fun main() {
    val delegateCollection = DelegateCollection(mutableListOf(1, 2, 3))
    println(delegateCollection.size)
    delegateCollection.addAll(listOf(4, 5, 6))
    delegateCollection.asReversed().forEach { println("Prefix $it") }
    val people = listOf("abc", "adadas", "")
    println(people.maxWithOrNull { o1, o2 -> o1.compareTo(o2) })
    var counter = 100
    var inc = { counter++ }
    println(inc())
    val text = StringBuilder().apply{
        append("a")
        append("b")
        append("c")
    }.toString()
    println(text)
    IntArray(20) {i -> i*i}.forEach { print(it) }
}