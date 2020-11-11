package ru.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.spring.error.UserErrorResponse;

@ControllerAdvice
public class UserRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(Exception e) {
        UserErrorResponse errorResponse = new UserErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }



}
