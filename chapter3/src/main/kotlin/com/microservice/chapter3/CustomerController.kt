package com.microservice.chapter3

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController {
    @RequestMapping(value = ["/customer"], method= [RequestMethod.GET])
    fun getCustomer() = "hello from a controller"
}