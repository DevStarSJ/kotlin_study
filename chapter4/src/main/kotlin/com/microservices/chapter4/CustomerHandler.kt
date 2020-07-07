package com.microservices.chapter4


import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.bodyToMono

@Component
class CustomerHandler(val customerService: CustomerService) {
    fun get(serverRequest: ServerRequest) = customerService.getCustomer(serverRequest.pathVariable("id").toInt())
        .flatMap { ok().body(fromObject(it)) }
        .switchIfEmpty(notFound().build()) // status(HttpStatus.NOT_FOUND).build()와 동일

    fun search(serverRequest: ServerRequest) = ok().body(customerService.searchCustomers(serverRequest.queryParam("nameFilter").orElse("")), Customer::class.java)

    fun create(serverRequest: ServerRequest) = customerService.createCustomer(serverRequest.bodyToMono()).flatMap {
        created(java.net.URI.create("/functional/customer/${it.id}")).build() }
}