package com.example.test_task.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskResponseTest {

    @Test
    public void testAllArgsConstructor() {
        Long id = 1L;
        String description = "Тестовое описание";
        boolean completed = true;

        TaskResponse response = new TaskResponse(id, description, completed);

        assertEquals(id, response.getId(), "ID должен совпадать");
        assertEquals(description, response.getDescription(), "Описание должно совпадать");
        assertTrue(response.isCompleted(), "Задача должна быть выполнена");
    }

    @Test
    public void testBuilder() {
        TaskResponse response = TaskResponse.builder()
                .id(2L)
                .description("Описание с помощью билдера")
                .completed(false)
                .build();

        assertEquals(2L, response.getId(), "ID должен совпадать");
        assertEquals("Описание с помощью билдера", response.getDescription(), "Описание должно совпадать");
        assertFalse(response.isCompleted(), "Задача не должна быть выполнена");
    }

    @Test
    public void testNoArgsConstructor() {
        TaskResponse response = new TaskResponse();

        assertNull(response.getId(), "ID должен быть null");
        assertNull(response.getDescription(), "Описание должно быть null");
        assertFalse(response.isCompleted(), "По умолчанию задача не должна быть выполнена");
    }
}
