package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.dto.Books;

//repository that extends CrudRepository  
public interface BooksRepository extends CrudRepository<Books, Integer> {
}
