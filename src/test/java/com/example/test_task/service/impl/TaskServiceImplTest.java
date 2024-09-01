package com.example.test_task.service.impl;

import com.example.test_task.dto.SimpleResponse;
import com.example.test_task.dto.TaskRequest;
import com.example.test_task.dto.TaskResponse;
import com.example.test_task.exception.BadRequestException;
import com.example.test_task.exception.NotFoundException;
import com.example.test_task.model.Task;
import com.example.test_task.repository.TaskRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceImplTest {

    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private TaskRepo taskRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveTask() {
        TaskRequest request = new TaskRequest("Test Description", true);
        Task task = new Task();
        task.setDescription(request.getDescription());
        task.setCompleted(request.isCompleted());

        when(taskRepo.save(any(Task.class))).thenReturn(task);

        SimpleResponse response = taskService.saveTask(request);

        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals("Успешно сохранен", response.getMessage());
        verify(taskRepo).save(any(Task.class));
    }

    @Test
    public void testGetTaskById() {
        Long id = 1L;
        Task task = new Task();
        task.setId(id);
        task.setDescription("Test Description");
        task.setCompleted(true);

        when(taskRepo.findById(id)).thenReturn(Optional.of(task));

        TaskResponse response = taskService.getTaskById(id);

        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals("Test Description", response.getDescription());
        assertTrue(response.isCompleted());
        verify(taskRepo).findById(id);
    }

    @Test
    public void testGetAllTasks() {
        TaskResponse taskResponse = new TaskResponse(1L, "Test Description", true);
        List<TaskResponse> taskList = Collections.singletonList(taskResponse);

        when(taskRepo.getAllTasks()).thenReturn(taskList);

        List<TaskResponse> response = taskService.getAllTasks();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(taskResponse, response.get(0));
        verify(taskRepo).getAllTasks();
    }

    @Test
    public void testUpdateTask() {
        Long id = 1L;
        TaskRequest request = new TaskRequest("Updated Description", false);
        Task oldTask = new Task();
        oldTask.setId(id);
        oldTask.setDescription("Old Description");
        oldTask.setCompleted(true);

        when(taskRepo.findById(id)).thenReturn(Optional.of(oldTask));
        when(taskRepo.save(any(Task.class))).thenReturn(oldTask);

        TaskResponse response = taskService.updateTask(id, request);

        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals("Updated Description", response.getDescription());
        assertFalse(response.isCompleted());
        verify(taskRepo).findById(id);
        verify(taskRepo).save(any(Task.class));
    }

    @Test
    public void testDeleteTaskById() {
        Long id = 1L;
        Task task = new Task();
        task.setId(id);
        task.setCompleted(true);

        when(taskRepo.findById(id)).thenReturn(Optional.of(task));
        doNothing().when(taskRepo).delete(any(Task.class));

        SimpleResponse response = taskService.deleteTaskById(id);

        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals("Task c ID " + id + " успешно удален", response.getMessage());
        verify(taskRepo).findById(id);
        verify(taskRepo).delete(any(Task.class));
    }

    @Test
    public void testDeleteTaskById_NotCompleted() {
        Long id = 1L;
        Task task = new Task();
        task.setId(id);
        task.setCompleted(false);

        when(taskRepo.findById(id)).thenReturn(Optional.of(task));

        BadRequestException thrown = assertThrows(
                BadRequestException.class,
                () -> taskService.deleteTaskById(id)
        );

        assertEquals(" Нельзя удалить не выполненую заданию", thrown.getMessage());
        verify(taskRepo).findById(id);
        verify(taskRepo, never()).delete(any(Task.class));
    }

    @Test
    public void testGetTaskById_NotFound() {
        Long id = 1L;

        when(taskRepo.findById(id)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(
                NotFoundException.class,
                () -> taskService.getTaskById(id)
        );

        assertEquals("Task c ID " + id + " не найден", thrown.getMessage());
        verify(taskRepo).findById(id);
    }
}
