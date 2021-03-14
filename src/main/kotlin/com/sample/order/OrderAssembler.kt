package com.sample.order

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.RepresentationModelAssembler
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.stereotype.Component

@Component
class OrderAssembler: RepresentationModelAssembler<Order, EntityModel<Order>> {
    override fun toModel(order: Order): EntityModel<Order> {
        return EntityModel.of(order,
                linkTo<OrderController>{ findOne(order.date) }.withSelfRel())
    }

}
