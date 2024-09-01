package com.example.test_task.dto;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleResponseTest {

    @Test
    public void testDefaultConstructor() {
        SimpleResponse response = new SimpleResponse();
        assertNull(response.getStatus(), "Статус должен быть null");
        assertNull(response.getMessage(), "Сообщение должно быть null");
    }

    @Test
    public void testAllArgsConstructor() {
        HttpStatus status = HttpStatus.OK;
        String message = "Успех";
        SimpleResponse response = new SimpleResponse(status, message);

        assertEquals(status, response.getStatus(), "Статус должен совпадать");
        assertEquals(message, response.getMessage(), "Сообщение должно совпадать");
    }

    @Test
    public void testBuilder() {
        SimpleResponse response = SimpleResponse.builder()
                .status(HttpStatus.CREATED)
                .message("Создано")
                .build();

        assertEquals(HttpStatus.CREATED, response.getStatus(), "Статус должен совпадать");
        assertEquals("Создано", response.getMessage(), "Сообщение должно совпадать");
    }

    @Test
    public void testMessageConstructor() {
        String message = "Произошла ошибка";
        SimpleResponse response = new SimpleResponse(message);

        assertNull(response.getStatus(), "Статус должен быть null");
        assertEquals(message, response.getMessage(), "Сообщение должно совпадать");
    }
}
