package com.example.test_task.service;

import com.example.test_task.dto.SimpleResponse;
import com.example.test_task.dto.TaskRequest;
import com.example.test_task.dto.TaskResponse;
import com.example.test_task.model.Task;
import com.example.test_task.repository.TaskRepo;
import com.example.test_task.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @InjectMocks
    private TaskServiceImpl taskService; // Замени на реальную реализацию TaskService

    @Mock
    private TaskRepo taskRepository; // Если у вас есть репозиторий для работы с данными

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveTask() {
        TaskRequest request = new TaskRequest();
        SimpleResponse expectedResponse = new SimpleResponse("Task saved successfully");

        when(taskRepository.save(any())).thenReturn(new Task()); // Заглушка для сохранения задачи

        SimpleResponse response = taskService.saveTask(request);

        assertEquals(expectedResponse, response);
        verify(taskRepository).save(any()); // Проверка, что метод save был вызван
    }

    @Test
    public void testGetTaskById() {
        Long id = 1L;
        TaskResponse expectedResponse = new TaskResponse(); // Настройте ваш ожидаемый ответ

        when(taskRepository.findById(id)).thenReturn(java.util.Optional.of(new Task())); // Заглушка для поиска задачи

        TaskResponse response = taskService.getTaskById(id);

        assertEquals(expectedResponse, response);
        verify(taskRepository).findById(id); // Проверка, что метод findById был вызван
    }

    @Test
    public void testGetAllTasks() {
        List<TaskResponse> expectedTasks = Collections.singletonList(new TaskResponse()); // Настройте ваш список задач

        when(taskRepository.findAll()).thenReturn(Collections.singletonList(new Task())); // Заглушка для получения всех задач

        List<TaskResponse> tasks = taskService.getAllTasks();

        assertEquals(expectedTasks, tasks);
        verify(taskRepository).findAll(); // Проверка, что метод findAll был вызван
    }

    @Test
    public void testUpdateTask() {
        Long id = 1L;
        TaskRequest request = new TaskRequest();
        TaskResponse expectedResponse = new TaskResponse(); // Настройте ваш ожидаемый ответ

        when(taskRepository.findById(id)).thenReturn(java.util.Optional.of(new Task())); // Заглушка для поиска задачи
        when(taskRepository.save(any())).thenReturn(new Task()); // Заглушка для обновления задачи

        TaskResponse response = taskService.updateTask(id, request);

        assertEquals(expectedResponse, response);
        verify(taskRepository).findById(id); // Проверка, что метод findById был вызван
        verify(taskRepository).save(any()); // Проверка, что метод save был вызван
    }

    @Test
    public void testDeleteTaskById() {
        Long id = 1L;
        SimpleResponse expectedResponse = new SimpleResponse("Task deleted successfully");

        doNothing().when(taskRepository).deleteById(id); // Заглушка для удаления задачи

        SimpleResponse response = taskService.deleteTaskById(id);

        assertEquals(expectedResponse, response);
        verify(taskRepository).deleteById(id); // Проверка, что метод deleteById был вызван
    }
}
