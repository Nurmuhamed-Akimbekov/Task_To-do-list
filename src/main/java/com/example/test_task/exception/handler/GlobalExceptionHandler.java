package com.example.test_task.exception.handler;

import com.example.test_task.exception.BadRequestException;
import com.example.test_task.exception.ExceptionResponse;
import com.example.test_task.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse notFoundException(NotFoundException e){
        return ExceptionResponse.builder()
                .message(e.getMessage())
                .exceptionClassName(e.getClass().getSimpleName())
                .httpStatus(HttpStatus.NOT_FOUND)
                .build();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse badRequestException(BadRequestException e){
        return ExceptionResponse.builder()
                .message(e.getMessage())
                .exceptionClassName(e.getClass().getSimpleName())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
    }
}