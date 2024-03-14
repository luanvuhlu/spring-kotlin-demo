package com.example.springkotlindemo

import com.example.springkotlindemo.entity.Message
import com.example.springkotlindemo.repository.MessageRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.Customization
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import org.skyscreamer.jsonassert.comparator.ArraySizeComparator
import org.skyscreamer.jsonassert.comparator.CustomComparator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus


@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class IntegrationTest(
    @Autowired private val restTemplate: TestRestTemplate,
    @Autowired private val messageRepository: MessageRepository
) {

    @BeforeEach
    fun setup() {
        println(">> Setup")
        messageRepository.deleteAll()
    }

    @Test
    fun `when findAll return message`() {
        messageRepository.saveAll(listOf(
            Message(text = "Hello world 1"),
            Message(text = "Hello world 2"),
            Message(text = "Hello world 3"),
        ))
        println(">> when findAll return messages")
        val entity = restTemplate.getForEntity<String>("/messages")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        JSONAssert.assertEquals(
            """[{
                "text": "Hello world 1"
                },
                {
                "text": "Hello world 2"
                },
                {
                "text": "Hello world 3"
                }
                
            ]""".trimMargin(), entity.body,
            CustomComparator(JSONCompareMode.LENIENT,
                Customization("text") { _, _ -> true })
        )
    }
}