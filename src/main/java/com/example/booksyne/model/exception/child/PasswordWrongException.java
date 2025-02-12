package com.example.booksyne.model.exception.child;

import org.apache.coyote.BadRequestException;

public class PasswordWrongException extends BadRequestException {
    public PasswordWrongException() {
        super("Old password entered incorrectly or new passwords do not match");
    }
}