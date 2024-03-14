package com.example.springkotlindemo.sample

data class Point(val x: Int, val y: Int)

operator fun Point.plus(other: Point) = Point(x + other.x, y + other.y)

operator fun Point.minus(other: Point) = Point(x + other.x, y + other.y)


fun main() {
    val a = Point(1, 2)
    val b = Point(3, 4)
    println(a + b)
    println(a - b)

    val list = mutableListOf(1, 2, 3)
    list += 4
    println(list)
}
