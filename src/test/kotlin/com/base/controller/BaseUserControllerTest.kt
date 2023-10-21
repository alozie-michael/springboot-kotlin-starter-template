package com.base.controller

import com.base.model.BaseIdResponse
import com.base.model.BaseUserDto
import com.base.model.BaseUserDtoPage
import com.base.TestUtils
import com.base.service.UserService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.util.UriComponentsBuilder

@WebMvcTest(controllers = [BaseUserController::class])
@AutoConfigureWebTestClient
class BaseUserControllerTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var userService: UserService

    @Test
    fun createUser() {
        //given
        val userDTO = TestUtils.createUserDto()
        val userId = TestUtils.USER_ID

        //when
        every { userService.createBaseUser(userDTO) } returns BaseIdResponse(id = userId)
        val res = webTestClient.post()
            .uri("/v1/base")
            .bodyValue(userDTO)
            .exchange()
            .expectStatus().isCreated
            .expectBody(BaseIdResponse::class.java)
            .returnResult()
            .responseBody

        //then
        Assertions.assertEquals(res!!.id, userId)
    }

    @Test
    fun getUser() {
        //given
        val userId = TestUtils.USER_ID
        val userDTO = TestUtils.createUserDto()

        //when
        every { userService.getUser(userId) } returns userDTO
        val res = webTestClient.get()
            .uri("/v1/base/$userId")
            .exchange()
            .expectStatus().isOk
            .expectBody(BaseUserDto::class.java)
            .returnResult()
            .responseBody

        //then
        Assertions.assertNotNull(res!!)
        Assertions.assertEquals(res.email, userDTO.email)
        Assertions.assertEquals(res.name, userDTO.name)
        Assertions.assertEquals(res.active, userDTO.active)
        Assertions.assertEquals(res.roles.get(0), userDTO.roles.get(0))
    }

    @Test
    fun getUsers() {
        //given
        val email = null;
        val baseUserDtoPage = TestUtils.createUserDtoPage()
        val pageable = PageRequest.of(1, 10)
        val uri = UriComponentsBuilder.fromUriString("/v1/base")
            .queryParam("email", email)
            .queryParam("page", pageable.pageNumber)
            .queryParam("pageSize", pageable.pageSize)
            .toUriString()

        //when
        every { userService.getUsers(email, pageable) } returns baseUserDtoPage
        val res = webTestClient.get()
            .uri(uri)
            .exchange()
            .expectStatus().isOk
            .expectBody(BaseUserDtoPage::class.java)
            .returnResult()
            .responseBody

        //then
        Assertions.assertNotNull(res!!)
        Assertions.assertEquals(res.pageSize, baseUserDtoPage.pageSize)
        Assertions.assertEquals(res.page, baseUserDtoPage.page)
        Assertions.assertEquals(res.totalPages, baseUserDtoPage.totalPages)
        Assertions.assertEquals(res.totalElements, baseUserDtoPage.totalElements)
        Assertions.assertEquals(res.data?.get(0)?.name, baseUserDtoPage.data?.get(0)?.name)
    }

    @Test
    fun deleteUser() {
        //given
        val userId = TestUtils.USER_ID

        //when
        every { userService.deleteUser(userId) } just Runs
        webTestClient.delete()
            .uri("/v1/base/$userId")
            .exchange()
            .expectStatus().isNoContent
    }
}