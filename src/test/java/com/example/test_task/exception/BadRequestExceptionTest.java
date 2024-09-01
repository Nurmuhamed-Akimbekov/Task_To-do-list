package com.example.test_task.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BadRequestExceptionTest {

    @Test
    public void testBadRequestExceptionWithMessage() {
        String expectedMessage = "Некорректный запрос";
        BadRequestException exception = new BadRequestException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testBadRequestExceptionWithoutMessage() {
        BadRequestException exception = new BadRequestException("");

        assertEquals("", exception.getMessage());
    }
}
