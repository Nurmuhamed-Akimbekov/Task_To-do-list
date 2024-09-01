package com.example.test_task.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskRequestTest {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    @Test
    public void testValidTaskRequest() {
        TaskRequest request = TaskRequest.builder()
                .description("Корректное описание")
                .completed(true)
                .build();

        Set<ConstraintViolation<TaskRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty(), "Ожидались отсутствия нарушений валидации");
    }

    @Test
    public void testInvalidTaskRequest_NoDescription() {
        TaskRequest request = TaskRequest.builder()
                .description("")
                .completed(true)
                .build();

        Set<ConstraintViolation<TaskRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty(), "Ожидались нарушения валидации");
        assertEquals(1, violations.size(), "Ожидалось одно нарушение валидации");

        ConstraintViolation<TaskRequest> violation = violations.iterator().next();
        assertEquals("Описание обязательно должно быть", violation.getMessage(), "Сообщение об ошибке не совпадает");
        assertEquals("description", violation.getPropertyPath().toString(), "Путь к свойству не совпадает");
    }

    @Test
    public void testAllArgsConstructor() {
        TaskRequest request = new TaskRequest("Тестовое описание", true);

        assertEquals("Тестовое описание", request.getDescription(), "Описание не совпадает");
        assertTrue(request.isCompleted(), "Статус выполнения не совпадает");
    }

    @Test
    public void testBuilder() {
        TaskRequest request = TaskRequest.builder()
                .description("Описание через билдера")
                .completed(false)
                .build();

        assertEquals("Описание через билдера", request.getDescription(), "Описание не совпадает");
        assertFalse(request.isCompleted(), "Статус выполнения не совпадает");
    }

    @Test
    public void testNoArgsConstructor() {
        TaskRequest request = new TaskRequest();

        assertNull(request.getDescription(), "Описание должно быть null");
        assertFalse(request.isCompleted(), "Статус выполнения должен быть false");
    }
}
