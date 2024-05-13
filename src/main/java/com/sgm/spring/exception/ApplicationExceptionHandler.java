package com.sgm.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(com.sgm.spring.exception.RecordNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String recordNotFoundHandler(com.sgm.spring.exception.RecordNotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler
    String recordNotFoundHandler(Exception exception) {
        return exception.getMessage();
    }
}
