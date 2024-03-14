package com.example.springkotlindemo.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/hello")
    fun index(@RequestParam(defaultValue = "Luan") name: String): String = "Hello $name"
}