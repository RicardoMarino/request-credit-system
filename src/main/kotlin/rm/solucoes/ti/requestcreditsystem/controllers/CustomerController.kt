package rm.solucoes.ti.requestcreditsystem.controllers

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import rm.solucoes.ti.requestcreditsystem.dtos.CustomerDTO
import rm.solucoes.ti.requestcreditsystem.dtos.CustomerUpdateDTO
import rm.solucoes.ti.requestcreditsystem.dtos.CustomerView
import rm.solucoes.ti.requestcreditsystem.entities.Customer
import rm.solucoes.ti.requestcreditsystem.services.ICustomerService


@RestController
@RequestMapping("/api/v1/customers")
class CustomerController (
    private val customerService: ICustomerService
){
    @PostMapping
    fun save(@RequestBody @Valid customerDTO: CustomerDTO): ResponseEntity<String> {
        val save = customerService.save(customerDTO.parseToEntity())
        return ResponseEntity.created(URI("/api/v1/customers/${save.id}")).body("Customer ${save.firstName} ${save.lastName} saved with success")
    }

    @GetMapping("/{customerId}")
    fun findById(@PathVariable customerId: Long): ResponseEntity<CustomerView> {
        val customer : Customer = customerService.findById(customerId)
        return ResponseEntity.ok().body(CustomerView(customer))
    }

    @DeleteMapping("/{customerId}")
    fun delete(@PathVariable customerId: Long) = customerService.delete(customerId)

    @PatchMapping
    fun update(@RequestParam("customerId") customerId: Long,
               @RequestBody @Valid customerUpdateDTO: CustomerUpdateDTO): ResponseEntity<CustomerView> {
        val customerToUpdate = customerUpdateDTO.parseToEntity(this.customerService.findById(customerId))
        val customer: Customer = this.customerService.save(customerToUpdate)
        return ResponseEntity.ok().body(CustomerView(customer))
    }
}