package com.example.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.entity.User;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {
}

