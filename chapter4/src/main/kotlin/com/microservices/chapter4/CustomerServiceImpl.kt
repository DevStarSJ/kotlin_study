package com.microservices.chapter4

import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.util.concurrent.ConcurrentHashMap

@Component
class CustomerServiceImpl : CustomerService {
    companion object {
        val initialCustomers = arrayOf(
            Customer(1, "Kotlin"),
            Customer(2, "Spring"),
            Customer(3, "Microservice", Customer.Telephone("+44", "1234567890")))
    }
    val customers = ConcurrentHashMap<Int, Customer>(initialCustomers.associateBy(Customer::id))

    override fun getCustomer(id: Int) = customers[id]?.toMono() ?: Mono.empty()

    override fun searchCustomers(nameFilter: String) = customers.filter {
        it.value.name.contains(nameFilter, true)
    }.map(Map.Entry<Int, Customer>::value).toFlux()

    override fun createCustomer(customerMono: Mono<Customer>): Mono<Customer> = customerMono.flatMap {
        if (customers[it.id] == null) {
            customers[it.id] = it
            it.toMono()
        } else {
            Mono.error(CustomerExistException("Customer ${it.id} already exist"))
        }
    }
}