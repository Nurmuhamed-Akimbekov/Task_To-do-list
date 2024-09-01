package com.example.test_task.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NotFoundExceptionTest {

    @Test
    public void testNotFoundExceptionWithMessage() {
        String expectedMessage = "не найден";
        NotFoundException exception = new NotFoundException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testNotFoundExceptionWithoutMessage() {
        NotFoundException exception = new NotFoundException("");

        assertEquals("", exception.getMessage());
    }
}
