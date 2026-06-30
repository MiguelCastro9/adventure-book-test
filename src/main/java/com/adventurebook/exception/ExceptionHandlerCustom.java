package com.adventurebook.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerCustom extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {

        var fieldError = ex.getBindingResult().getFieldErrors().stream().findFirst();

        MessageException message = fieldError
                .map(error -> new MessageException(error.getDefaultMessage(), error.toString()))
                .orElse(new MessageException("Validation error", ex.toString()));

        return handleExceptionInternal(ex, message, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(MessageCustomException.class)
    public ResponseEntity<Object> handleMessageCustomException(MessageCustomException ex) {
        MessageException message = new MessageException(ex.getMessage(), ex.toString());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }
    
}
