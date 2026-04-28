package com.example.SimplestCRUDExample.exception;

public class DeleteConfirmationRequiredException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public DeleteConfirmationRequiredException(String message) {
        super(message);
    }
}