package com.example.reactiveAPI.controller;

import com.example.reactiveAPI.model.Customer;
import com.example.reactiveAPI.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public Flux<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<Customer>> getCustomer(@PathVariable Long id) {
        return customerRepository.findById(id)
                .map(customer -> ResponseEntity.ok(customer))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Customer> saveCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<Customer>> updateCustomer(@PathVariable(value = "id") Long id,
                                                       @RequestBody Customer customer) {
        return customerRepository.findById(id)
                .flatMap(existingCustomer -> {
                    existingCustomer.setFirstName(customer.getFirstName());
                    existingCustomer.setLastName(customer.getLastName());
                    return customerRepository.save(existingCustomer);
                })
                .map(updateProduct -> ResponseEntity.ok(updateProduct))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteCustomer(@PathVariable(value = "id") Long id) {
        return customerRepository.findById(id)
                .flatMap(existingCustomer ->
                        customerRepository.delete(existingCustomer)
                                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}