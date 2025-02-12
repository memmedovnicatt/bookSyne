package com.example.booksyne.model.exception;

import com.example.booksyne.model.exception.child.*;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import javax.security.sasl.AuthenticationException;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
@Slf4j
public class GlobalHandlerException {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<String> handleAlreadyExistException(AlreadyExistsException alreadyExistsException) {
        log.error("AlreadyExistException ->  {}", alreadyExistsException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(alreadyExistsException.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException notFoundException) {
        log.error("NotFoundException ->  {}", notFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundException.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException ex) {
        log.error("BadRequestException ->  {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalStateException(IllegalArgumentException illegalArgumentException) {
        log.error("IllegalStateException-> {}", illegalArgumentException.getMessage());
        return illegalArgumentException.getMessage();
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException ex) {
        log.error("AuthenticationException ->  {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleMaxValueExceededException(ConstraintViolationException ex) {
        log.error("ConstraintViolationException ->  {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        log.error("HttpMessageNotReadableException ->  {}", ex.getMessage());
        return new ResponseEntity<>("Invalid JSON data", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        log.error("Validation error occurred: {}", errors);

        ExceptionDTO exceptionDTO = new ExceptionDTO(
                HttpStatus.BAD_REQUEST.value(),
                "Validation error",
                errors
        );

        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleUnexpectedTypeException(UnexpectedTypeException unexpectedTypeException) {
        log.error("Action error.log->Unexpected_Type_Exception {}", unexpectedTypeException.getMessage());
        return unexpectedTypeException.getMessage();
    }
}