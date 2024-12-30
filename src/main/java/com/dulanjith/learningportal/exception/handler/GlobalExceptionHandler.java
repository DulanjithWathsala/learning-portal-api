package com.dulanjith.learningportal.exception.handler;

import com.dulanjith.learningportal.exception.ErrorResponse;
import com.dulanjith.learningportal.exception.ResourceNotFoundException;
import com.dulanjith.learningportal.exception.UserAlreadyExistsException;
import com.dulanjith.learningportal.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistException(
            UserAlreadyExistsException ex) {
        log.error("User already exists exception occurred | {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .status(HttpStatus.BAD_REQUEST).message(ex.getMessage()).build());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistException(
            UserNotFoundException ex) {
        log.error("User not found exception occurred | {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .status(HttpStatus.NOT_FOUND).message(ex.getMessage()).build());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistException(
            ResourceNotFoundException ex) {
        log.error("Resource not found exception occurred | {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .status(HttpStatus.NOT_FOUND).message(ex.getMessage()).build());
    }
}
