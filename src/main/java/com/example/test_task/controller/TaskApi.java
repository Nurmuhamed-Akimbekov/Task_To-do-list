package com.example.test_task.controller;

import com.example.test_task.dto.SimpleResponse;
import com.example.test_task.dto.TaskRequest;
import com.example.test_task.dto.TaskResponse;
import com.example.test_task.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskApi {
    private final TaskService taskService;
    @GetMapping
    public List<TaskResponse> getAllTasks(){
        return taskService.getAllTasks();
    }
    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }
    @PostMapping
    public SimpleResponse createTask(@RequestBody @Valid TaskRequest taskRequest){
        return taskService.saveTask(taskRequest);
    }
    @PostMapping("/{taskId}")
    public TaskResponse updateTask(@PathVariable Long taskId,@RequestBody @Valid TaskRequest taskRequest){
        return taskService.updateTask(taskId,taskRequest);
    }
    @DeleteMapping("/{id}")
    public SimpleResponse deleteTask(@PathVariable Long id){
        return taskService.deleteTaskById(id);
    }
}
