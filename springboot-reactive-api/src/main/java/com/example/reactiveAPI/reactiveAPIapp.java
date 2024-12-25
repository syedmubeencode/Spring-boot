package com.example.reactiveAPI;

import com.example.reactiveAPI.model.Customer;
import com.example.reactiveAPI.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.util.Arrays;

@SpringBootApplication
public class reactiveAPIapp {

	public static void main(String[] args) {
		SpringApplication.run(reactiveAPIapp.class, args);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// Create customers in H2 database
			repository.saveAll(Arrays.asList(new Customer("Angelina", "Tan"),
							new Customer("Brody", "Lim"),
							new Customer("Claude", "See"),
							new Customer("Dexter", "Ang"),
							new Customer("Eugene", "Tsang")))
					.blockLast(Duration.ofSeconds(10));
			//blockLast: Block indefinitely until the upstream signals its last value or completes
		};
	}

}
