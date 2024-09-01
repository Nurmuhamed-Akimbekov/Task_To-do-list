package com.example.test_task.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

public class ExceptionResponseTest {

    @Test
    public void testRecordConstructor() {
        String exceptionClassName = "NotFoundException";
        String message = "Task not found";
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ExceptionResponse response = new ExceptionResponse(exceptionClassName, message, httpStatus);

        assertEquals(exceptionClassName, response.exceptionClassName());
        assertEquals(message, response.message());
        assertEquals(httpStatus, response.httpStatus());
    }

    @Test
    public void testBuilder() {
        String exceptionClassName = "BadRequestException";
        String message = "Invalid request";
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ExceptionResponse response = ExceptionResponse.builder()
                .exceptionClassName(exceptionClassName)
                .message(message)
                .httpStatus(httpStatus)
                .build();

        assertEquals(exceptionClassName, response.exceptionClassName());
        assertEquals(message, response.message());
        assertEquals(httpStatus, response.httpStatus());
    }
}
