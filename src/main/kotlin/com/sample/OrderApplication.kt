package com.sample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.sample.*")
class OrderApplication

fun main(args: Array<String>) {
	runApplication<OrderApplication>(*args)
}
