package com.rbobko.spiralproject.web.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = EmptyResultDataAccessException.class)
    public final ResponseEntity<Object> handleEmptyResultException(Exception e, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        return handleExceptionInternal(e, null, headers, HttpStatus.NOT_FOUND, request);
    }

}
