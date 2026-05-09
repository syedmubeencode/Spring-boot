package com.receipt.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products") // Maps to the table we created in pgAdmin
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	private String itemId;

	private String description;

	private Double price;
}