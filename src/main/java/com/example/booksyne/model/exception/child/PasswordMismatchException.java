package com.example.booksyne.model.exception.child;

import com.example.booksyne.model.exception.child.PasswordMismatchException;
public class PasswordMismatchException extends BadRequestException {
    public PasswordMismatchException() {
        super("Retry password do not matching new password");
    }
}