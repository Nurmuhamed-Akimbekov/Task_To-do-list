package com.example.test_task.model;

import com.example.test_task.repository.TaskRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class TaskTest {

    @Autowired
    private TaskRepo taskRepo;

    @Test
    public void testCreateTask() {
        Task task = new Task();
        task.setDescription("Описание тестовой задачи");
        task.setCompleted(false);

        Task savedTask = taskRepo.save(task);

        assertNotNull(savedTask.getId(), "ID должен быть сгенерирован после сохранения");
        assertEquals("Описание тестовой задачи", savedTask.getDescription(), "Описание должно совпадать");
        assertFalse(savedTask.isCompleted(), "Задача не должна быть завершена");
    }

    @Test
    public void testFindById() {
        Task task = new Task();
        task.setDescription("Задача для поиска");
        task.setCompleted(true);
        Task savedTask = taskRepo.save(task);

        Task foundTask = taskRepo.findById(savedTask.getId()).orElse(null);

        assertNotNull(foundTask, "Задача должна быть найдена по ID");
    }
}