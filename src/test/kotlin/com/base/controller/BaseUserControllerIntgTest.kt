package com.base.controller

import com.base.model.BaseIdResponse
import com.base.model.BaseUserDto
import com.base.model.BaseUserDtoPage
import com.base.TestUtils
import com.base.configuration.ContainerInitializer
import com.base.configuration.DatabaseInitializer
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.util.UriComponentsBuilder
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class BaseUserControllerIntgTest: DatabaseInitializer() {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun createUser() {
        //given
        val userDTO = TestUtils.createUserDto()

        //when
        val res = webTestClient.post()
            .uri("/v1/base")
            .bodyValue(userDTO)
            .exchange()
            .expectStatus().isCreated
            .expectBody(BaseIdResponse::class.java)
            .returnResult()
            .responseBody

        //then
        Assertions.assertNotNull(res!!.id)
    }

    @Test
    fun getUser() {
        //given
        val userId = TestUtils.USER_ID

        //when
        val res = webTestClient.get()
            .uri("/v1/base/$userId")
            .exchange()
            .expectStatus().isOk
            .expectBody(BaseUserDto::class.java)
            .returnResult()
            .responseBody

        //then
        Assertions.assertNotNull(res!!)
        Assertions.assertEquals("m@gmail.com", res.email)
        Assertions.assertEquals("Michael Alozie", res.name)
        Assertions.assertTrue(res.active)
    }

    @Test
    fun getUsers() {
        //given
        val pageable = PageRequest.of(0, 10)
        val uri = UriComponentsBuilder.fromUriString("/v1/base")
            .queryParam("page", pageable.pageNumber)
            .queryParam("pageSize", pageable.pageSize)
            .toUriString()

        //when
        val res = webTestClient.get()
            .uri(uri)
            .exchange()
            .expectStatus().isOk
            .expectBody(BaseUserDtoPage::class.java)
            .returnResult()
            .responseBody

        //then
        Assertions.assertNotNull(res!!)
        Assertions.assertEquals(10, res.pageSize)
        Assertions.assertEquals(0, res.page)
        Assertions.assertEquals(1, res.totalPages)
        Assertions.assertTrue(res.totalElements > 0)
        Assertions.assertTrue(res.data?.isNotEmpty() ?: false)
    }

    @Test
    fun deleteUser() {
        //given
        val userId = TestUtils.USER_ID_TO_DELETE

        //when
        webTestClient.delete()
            .uri("/v1/base/$userId")
            .exchange()
            .expectStatus().isNoContent
    }
}