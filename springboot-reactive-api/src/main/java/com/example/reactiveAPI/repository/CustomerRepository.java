package com.example.reactiveAPI.repository;

import com.example.reactiveAPI.model.Customer;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;

/**
 * Based on Spring Data R2DBC: https://spring.io/projects/spring-data-r2dbc
 * Reactive database drivers
 */
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {

    @Query("SELECT * FROM customer WHERE last_name = :lastname")
    Flux<Customer> findByLastName(String lastName);

}