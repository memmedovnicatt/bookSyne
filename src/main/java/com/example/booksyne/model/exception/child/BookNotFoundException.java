package com.example.booksyne.model.exception.child;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String message) {
        super(message);
    }
}
