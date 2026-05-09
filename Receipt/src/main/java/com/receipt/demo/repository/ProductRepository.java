package com.receipt.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.receipt.demo.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}