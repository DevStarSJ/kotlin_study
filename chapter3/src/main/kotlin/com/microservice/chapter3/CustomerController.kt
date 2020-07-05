package com.microservice.chapter3

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap

@RestController
class CustomerController {
    @Autowired
    lateinit var customers: ConcurrentHashMap<Int, Customer>

    @RequestMapping(value = ["/customers"], method= [RequestMethod.GET])
    fun getCustomers(@RequestParam(required = false, defaultValue = "") nameFilter: String) =
        customers.filter {
            it.value.name.contains(nameFilter, true)
        }.map(Map.Entry<Int, Customer>::value).toList()

    @RequestMapping(value = ["/customer/{id}"], method = [RequestMethod.GET])
    fun getCustomer(@PathVariable id: Int) = customers[id]
}