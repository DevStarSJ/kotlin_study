package com.microservice.chapter2

import org.springframework.stereotype.Service

@Service
class ExampleService: ServiceInterface {
    override fun getHello(name: String) = "hello $name"
}