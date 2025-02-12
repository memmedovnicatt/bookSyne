package com.example.booksyne.model.exception.child;

public class AlreadyExistsException extends RuntimeException {
  public AlreadyExistsException(String message) {
    super(message);
  }
}
