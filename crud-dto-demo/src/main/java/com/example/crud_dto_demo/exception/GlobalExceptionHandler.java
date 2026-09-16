package com.example.crud_dto_demo.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.crud_dto_demo.dto.ExceptionResponseDTO;
import com.example.crud_dto_demo.dto.ValidationExceptionResponseDTO;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice 
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponseDTO> handleRunTimeException(RuntimeException ex, HttpServletRequest req) {

        ExceptionResponseDTO exceptionResponse = new ExceptionResponseDTO(
            LocalDateTime.now(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            ex.getMessage(),
            req.getRequestURI()
        ); 

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionResponse);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest req) {

        ExceptionResponseDTO exceptionResponse = new ExceptionResponseDTO(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            HttpStatus.NOT_FOUND.getReasonPhrase(),
            ex.getMessage(),
            req.getRequestURI()
        ); 

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionResponse);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ExceptionResponseDTO> handleDuplicateResourceException(DuplicateResourceException ex, HttpServletRequest req) {

        ExceptionResponseDTO exceptionResponse = new ExceptionResponseDTO(
            LocalDateTime.now(),
            HttpStatus.CONFLICT.value(),
            HttpStatus.CONFLICT.getReasonPhrase(),
            ex.getMessage(),
            req.getRequestURI()
        ); 

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exceptionResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest req) {

        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach( error -> 
                    fieldErrors.put(error.getField(), error.getDefaultMessage()));

        ValidationExceptionResponseDTO exceptionResponse = new ValidationExceptionResponseDTO(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            "Validation Failed",
            req.getRequestURI(),
            fieldErrors
        ); 

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionResponse);
    }
}
