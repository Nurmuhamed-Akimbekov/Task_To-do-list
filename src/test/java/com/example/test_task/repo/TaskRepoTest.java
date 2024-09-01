package com.example.test_task.repo;

import com.example.test_task.dto.TaskResponse;
import com.example.test_task.model.Task;
import com.example.test_task.repository.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class TaskRepoTest {

    @Autowired
    private TaskRepo taskRepo;

    @BeforeEach
    public void setUp() {
        Task task1 = new Task(null, "Задача 1", false);
        Task task2 = new Task(null, "Задача 2", true);
        taskRepo.save(task1);
        taskRepo.save(task2);
    }

    @Test
    public void testGetAllTasks() {
        List<TaskResponse> taskResponses = taskRepo.getAllTasks();
        assertNotNull(taskResponses, "Список задач не должен быть null");
        assertEquals(2, taskResponses.size(), "Должно быть две задачи в списке");

        TaskResponse response1 = taskResponses.get(0);
        TaskResponse response2 = taskResponses.get(1);

        assertNotNull(response1.getId(), "ID задачи не должен быть null");
        assertNotNull(response2.getId(), "ID задачи не должен быть null");

        assertEquals("Задача 1", response1.getDescription(), "Описание задачи должно совпадать");
        assertEquals("Задача 2", response2.getDescription(), "Описание задачи должно совпадать");

        assertFalse(response1.isCompleted(), "Задача 1 не должна быть выполнена");
        assertTrue(response2.isCompleted(), "Задача 2 должна быть выполнена");
    }
}