package com.sample.order

import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
class OrderController(private val repository: OrderRepository
              ,private val assembler: OrderAssembler) {

    @GetMapping("/api/v1/order/{id}")
    fun findOne(@PathVariable id: Long): EntityModel<Order> {
        val order = repository.findById(id)
        if (order.isPresent) {
            return assembler.toModel(order.get())
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND, "This order does not exist")
    }

    @GetMapping("/api/v1/orders")
    fun all(): CollectionModel<EntityModel<Order>> {
        val orders = repository.findAll().map {
            assembler.toModel(it)
        }
        return CollectionModel.of(orders,  //
                linkTo<OrderController>{all()}.withRel("all"))
    }
}
