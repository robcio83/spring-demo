package com.sample.order

import com.ninjasquad.springmockk.MockkBean
import com.ninjasquad.springmockk.SpykBean
import com.sample.OrderApplication
import com.sample.user.UserAssembler
import com.sample.user.UserRepository
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

@WebMvcTest
class OrderControllerTest(@Autowired val mockMvc: MockMvc) {

    //Why we need that to have test compilable ?
    /*
    @MockkBean
    private lateinit var userRepository: UserRepository

    @SpykBean
    private lateinit var userAssembler: UserAssembler
    */

    @MockkBean
    private lateinit var orderRepository: OrderRepository

    @SpykBean
    private lateinit var orderAssembler: OrderAssembler

    @Test
    fun `Should form expected json response with order data`(){
        val order = Order(id = 7, date = 1501212321)
        every { orderRepository.findById(any()) } returns Optional.of(order)
        mockMvc.perform(get("/api/v1/order/7").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.id").value(order.id))
    }
}