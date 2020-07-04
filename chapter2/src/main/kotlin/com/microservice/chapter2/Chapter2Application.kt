package com.microservice.chapter2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@SpringBootApplication
class Chapter2Application

@Controller
class FirstController(val exampleService: ExampleService) {
	@RequestMapping(value = ["/user/{name}"], method = arrayOf(RequestMethod.GET))
	@ResponseBody
	fun hello(@PathVariable name: String) = exampleService.getHello(name)
}

fun main(args: Array<String>) {
	runApplication<Chapter2Application>(*args)
}
