package rm.solucoes.ti.requestcreditsystem.services.implement

import org.springframework.stereotype.Service
import rm.solucoes.ti.requestcreditsystem.entities.Customer
import rm.solucoes.ti.requestcreditsystem.repositories.ICustomerRepository
import rm.solucoes.ti.requestcreditsystem.services.ICustomerService

@Service
class CustomerService(
        private val customerRepository: ICustomerRepository
) : ICustomerService {
    override fun save(customer: Customer): Customer = this.customerRepository.save(customer);

    override fun findAll(): List<Customer> = this.customerRepository.findAll()

    override fun findById(id: Long): Customer = this.customerRepository.findById(id).orElseThrow{
        throw Exception("Customer id: $id not found")
    }
    override fun delete(id: Long) = this.customerRepository.deleteById(id)
}