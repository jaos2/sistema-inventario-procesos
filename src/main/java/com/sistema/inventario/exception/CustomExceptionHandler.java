package com.sistema.inventario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleMyCustomException(NotFoundException ex) {
        Map<String, String> response = new HashMap();
        response.put("Date: ", LocalDate.now().toString());
        response.put("Message: ", ex.getMessage());
        return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Object> handleAlreadyExistsException(AlreadyExistsException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("Date: ", LocalDate.now().toString());
        response.put("Message: ", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(
     MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> body = new HashMap<>();
        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
        body.put("error", errorMessage);
    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
}
    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<?> handleAuthenticationFailedException(AuthenticationFailedException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDate.now().toString());
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }
}
