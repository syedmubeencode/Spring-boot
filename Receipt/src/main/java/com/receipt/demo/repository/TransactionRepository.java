package com.receipt.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.receipt.demo.model.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
}