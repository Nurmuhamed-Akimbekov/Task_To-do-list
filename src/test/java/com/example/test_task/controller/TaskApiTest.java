package com.example.test_task.controller;

import com.example.test_task.dto.SimpleResponse;
import com.example.test_task.dto.TaskRequest;
import com.example.test_task.dto.TaskResponse;
import com.example.test_task.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskApi.class)
public class TaskApiTest {

    @InjectMocks
    private TaskApi taskApi;

    @Mock
    private TaskService taskService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(WebApplicationContext wac) {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetAllTasks() throws Exception {
        TaskResponse taskResponse = new TaskResponse(1L, "Test Description", true);
        when(taskService.getAllTasks()).thenReturn(List.of(taskResponse));

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(taskResponse))));
    }

    @Test
    public void testGetTaskById() throws Exception {
        Long id = 1L;
        TaskResponse taskResponse = new TaskResponse(id, "Test Description", true);
        when(taskService.getTaskById(id)).thenReturn(taskResponse);

        mockMvc.perform(get("/api/tasks/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(taskResponse)));
    }

    @Test
    public void testCreateTask() throws Exception {
        TaskRequest taskRequest = new TaskRequest("Test Description", true);
        SimpleResponse simpleResponse = new SimpleResponse(HttpStatus.OK, "Успешно сохранен");
        when(taskService.saveTask(taskRequest)).thenReturn(simpleResponse);

        mockMvc.perform(post("/api/tasks")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(taskRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(simpleResponse)));
    }

    @Test
    public void testUpdateTask() throws Exception {
        Long taskId = 1L;
        TaskRequest taskRequest = new TaskRequest("Updated Description", false);
        TaskResponse taskResponse = new TaskResponse(taskId, "Updated Description", false);
        when(taskService.updateTask(taskId, taskRequest)).thenReturn(taskResponse);

        mockMvc.perform(post("/api/tasks/{taskId}", taskId)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(taskRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(taskResponse)));
    }

    @Test
    public void testDeleteTask() throws Exception {
        Long id = 1L;
        SimpleResponse simpleResponse = new SimpleResponse(HttpStatus.OK, "Task c ID " + id + " успешно удален");
        when(taskService.deleteTaskById(id)).thenReturn(simpleResponse);

        mockMvc.perform(delete("/api/tasks/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(simpleResponse)));
    }
}
