package com.sample.user

import com.ninjasquad.springmockk.MockkBean
import com.ninjasquad.springmockk.SpykBean
import com.sample.order.OrderAssembler
import com.sample.order.OrderRepository
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
class UserControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var userRepository: UserRepository

    @SpykBean
    private lateinit var userAssembler: UserAssembler

    /*
    @MockkBean
    private lateinit var orderRepository: OrderRepository

    @SpykBean
    private lateinit var orderAssembler: OrderAssembler
    */

    @Test
    fun `Should form expected json response with user data`(){
        val user = User(id = 5, login = "john.doe@aol.com", firstname = "John", lastname = "Doe")
        every { userRepository.findAll() } returns listOf(user)
        mockMvc.perform(get("/api/v1/users").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
    }
}