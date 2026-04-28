package com.example.SimplestCRUDExample.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SimplestCRUDExample.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
//	// Spring generates the "SELECT * FROM books WHERE title = ?" query automatically
//    Optional<Book> findByTitle(String title);
	
	// Return a List instead of Optional
    List<Book> findByTitle(String title);
}
