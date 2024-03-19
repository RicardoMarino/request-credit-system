package rm.solucoes.ti.requestcreditsystem.services

import rm.solucoes.ti.requestcreditsystem.entities.Customer

interface ICustomerService {
    fun save(customer:Customer) : Customer
    fun findAll() : List<Customer>
    fun findById(id:Long) : Customer
    fun delete(id:Long)
}