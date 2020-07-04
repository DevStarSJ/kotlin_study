package com.microservice.chapter2

import org.springframework.stereotype.Service

@Service
class ExampleService {
    fun getHello(name: String) = "hello $name"
}