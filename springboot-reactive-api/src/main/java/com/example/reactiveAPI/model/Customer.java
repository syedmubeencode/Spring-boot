package com.example.reactiveAPI.model;


import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Customer {

    @Id
    private Long id;

    private String firstName;

    private String lastName;

    public Customer(String firstName, String lastName){
        this.firstName  = firstName;
        this.lastName = lastName;
    }
}
