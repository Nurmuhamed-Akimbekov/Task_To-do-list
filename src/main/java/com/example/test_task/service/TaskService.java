package com.example.test_task.service;

import com.example.test_task.dto.SimpleResponse;
import com.example.test_task.dto.TaskRequest;
import com.example.test_task.dto.TaskResponse;

import java.util.List;

public interface TaskService {
    SimpleResponse saveTask(TaskRequest taskRequest);
    TaskResponse getTaskById(Long id);
    List<TaskResponse> getAllTasks();
    TaskResponse updateTask(Long id,TaskRequest taskRequest);
    SimpleResponse deleteTaskById(Long id);
}
