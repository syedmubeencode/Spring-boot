package com.example.SimplestCRUDExample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.SimplestCRUDExample.model.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleBookExists(BookAlreadyExistsException ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.CONFLICT.value(), 
            ex.getMessage(), 
            "Ensure the book title or ISBN is unique before submitting."
        );
        
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(), 
            "Something went wrong", 
            ex.getMessage()
        );
        
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBookNotFound(BookNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            "The database is currently empty or the requested resource does not exist."
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(DeleteConfirmationRequiredException.class)
    public ResponseEntity<ErrorResponse> handleDeleteConfirmation(DeleteConfirmationRequiredException ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.PRECONDITION_REQUIRED.value(), // 428 is a great code for this
            ex.getMessage(),
            "Please append '?confirm=true' to your URL to proceed with deletion."
        );
        return new ResponseEntity<>(error, HttpStatus.PRECONDITION_REQUIRED);
    }
}