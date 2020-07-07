package com.microservices.chapter4


import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.notFound
import org.springframework.web.reactive.function.server.ServerResponse.ok

@Component
class CustomerHandler(val customerService: CustomerService) {
    fun get(serverRequest: ServerRequest) = customerService.getCustomer(serverRequest.pathVariable("id").toInt())
        .flatMap { ok().body(fromObject(it)) }
        .switchIfEmpty(notFound().build()) // status(HttpStatus.NOT_FOUND).build()와 동일
}