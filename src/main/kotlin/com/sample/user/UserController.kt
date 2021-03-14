package com.sample.user

import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
class UserController(private val repository: UserRepository,
                     private val assembler: UserAssembler) {

    @GetMapping("/api/v1/user/{id}")
    fun findOne(@PathVariable id: Long): EntityModel<User> {
        val user = repository.findById(id)
        if (user.isPresent) {
            return assembler.toModel(user.get())
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND, "This user does not exist")
    }

    @GetMapping("/api/v1/users")
    fun all(): CollectionModel<EntityModel<User>> {
        val users = repository.findAll().map {
            assembler.toModel(it)
        }
        return CollectionModel.of(users,
                linkTo<UserController>{all()}.withRel("all"))
    }
}

