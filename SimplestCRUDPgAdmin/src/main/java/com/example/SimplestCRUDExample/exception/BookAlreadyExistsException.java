package com.example.SimplestCRUDExample.exception;

public class BookAlreadyExistsException extends RuntimeException {
    
    // Add this line to resolve the warning
    private static final long serialVersionUID = 1L;

    public BookAlreadyExistsException(String message) {
        super(message);
    }
}