package com.example.booksyne.model.exception.child;

import org.springframework.data.crossstore.ChangeSetPersister;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(String message) {
        super(message);
    }
}
