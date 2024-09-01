package com.example.test_task.exception;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record ExceptionResponse(
        String exceptionClassName,
        String message,
        HttpStatus httpStatus)  {
}