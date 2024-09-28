package com.example.booksyne.model.exception.child;

import org.springframework.data.crossstore.ChangeSetPersister;

public class BagNotFoundException extends RuntimeException {
    public BagNotFoundException(String message) {
        super(message);
    }
}
