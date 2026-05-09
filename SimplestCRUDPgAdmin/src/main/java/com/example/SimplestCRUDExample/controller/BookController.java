package com.example.SimplestCRUDExample.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SimplestCRUDExample.exception.BookAlreadyExistsException;
import com.example.SimplestCRUDExample.exception.BookNotFoundException;
import com.example.SimplestCRUDExample.exception.DeleteConfirmationRequiredException;
import com.example.SimplestCRUDExample.model.Book;
import com.example.SimplestCRUDExample.repo.BookRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api")
@Tag(name = "Book Controller", description = "Management APIs for Books")
public class BookController {

	@Autowired
	BookRepository bookRepository;

	@Operation(summary = "Retrieve all books", description = "Gets a list of all books in the database")
	@GetMapping("/getAllBooks")
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> bookList = bookRepository.findAll();

		if (bookList.isEmpty()) {
			// This will trigger the handleBookNotFound method in your
			// GlobalExceptionHandler
			throw new BookNotFoundException("No books were found in the library.");
		}

		return new ResponseEntity<>(bookList, HttpStatus.OK);
	}

	@Operation(summary = "Get a book by ID")
	@GetMapping("/getBookById/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id) {
		Optional<Book> bookObj = bookRepository.findById(id);
		if (bookObj.isPresent()) {
			return new ResponseEntity<>(bookObj.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/addBook")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		List<Book> existingBooks = bookRepository.findByTitle(book.getTitle());

		if (!existingBooks.isEmpty()) {
			// This triggers the GlobalExceptionHandler
			throw new BookAlreadyExistsException("Book with title '" + book.getTitle() + "' already exists.");
		}

		return new ResponseEntity<>(bookRepository.save(book), HttpStatus.CREATED);
	}

	@Operation(summary = "This will update the book")
	@PostMapping("/updateBook/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
		// .orElseThrow() is a clean way to handle the "Not Found" case in one line
		Book updatedBookData = bookRepository.findById(id)
				.orElseThrow(() -> new BookNotFoundException("Cannot update. Book with ID " + id + " not found."));

		// If found, update the fields
		updatedBookData.setTitle(book.getTitle());
		updatedBookData.setAuthor(book.getAuthor());

		// Save and return
		Book bookObj = bookRepository.save(updatedBookData);
		return new ResponseEntity<>(bookObj, HttpStatus.OK);
	}

	@Operation(summary = "This will delete book by id with confirmation")
	@DeleteMapping("/deleteBookById/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable Long id,
			@RequestParam(value = "confirm", defaultValue = "false") boolean confirm) {

		// 1. Check if the book exists first
		if (!bookRepository.existsById(id)) {
			throw new BookNotFoundException("Cannot delete. Book with ID " + id + " not found.");
		}

		// 2. Check for confirmation flag
		if (!confirm) {
			throw new DeleteConfirmationRequiredException("Are you sure you want to delete book ID " + id + "?");
		}

		// 3. Perform the delete
		bookRepository.deleteById(id);
		return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
	}

	@Operation(summary = "This will delete all books with confirmation")
	@DeleteMapping("/deleteAllBooks")
	public ResponseEntity<String> deleteAllBooks(
			@RequestParam(value = "confirm", defaultValue = "false") boolean confirm) {

		// 1. Check if there is actually anything to delete
		if (bookRepository.count() == 0) {
			throw new BookNotFoundException("No books found to delete. The library is already empty.");
		}

		// 2. Check for the confirmation flag
		if (!confirm) {
			throw new DeleteConfirmationRequiredException(
					"WARNING: You are about to delete ALL books. This action cannot be undone!");
		}

		// 3. Perform bulk delete
		bookRepository.deleteAll();

		return new ResponseEntity<>("All books have been successfully deleted.", HttpStatus.OK);
	}

}
