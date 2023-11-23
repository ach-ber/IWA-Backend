package com.iwa.ktlanding

import org.springframework.core.io.ClassPathResource
import org.springframework.util.StreamUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.nio.charset.StandardCharsets

@RestController
class MessageController {

    @GetMapping("/name")
    fun name(@RequestParam("name") name: String) = "Hello, $name!"

    @GetMapping("/")
    fun index() = "<h1>test</h1>"

    @GetMapping("/landing")
    fun landing(): String {
        val resource = ClassPathResource("templates/landing.html")
        return StreamUtils.copyToString(resource.inputStream, StandardCharsets.UTF_8)
    }
}