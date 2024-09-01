package com.example.test_task.service.impl;

import com.example.test_task.dto.SimpleResponse;
import com.example.test_task.dto.TaskRequest;
import com.example.test_task.dto.TaskResponse;
import com.example.test_task.exception.BadRequestException;
import com.example.test_task.exception.NotFoundException;
import com.example.test_task.model.Task;
import com.example.test_task.repository.TaskRepo;
import com.example.test_task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;

    @Override
    public SimpleResponse saveTask(TaskRequest taskRequest) {
        Task task = new Task();
        task.setDescription(taskRequest.getDescription());
        task.setCompleted(taskRequest.isCompleted());
        taskRepo.save(task);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Успешно сохранен").build();
    }

    @Override
    public TaskResponse getTaskById(Long id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new NotFoundException("Task c ID " + id + " не найден"));
        return TaskResponse.builder().id(task.getId()).description(task.getDescription()).completed(task.isCompleted()).build();
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        List<TaskResponse> allTasks = taskRepo.getAllTasks();
        if (allTasks.isEmpty()) {
            return Collections.emptyList();
        }
        return allTasks;
    }

    @Override
    public TaskResponse updateTask(Long id, TaskRequest taskRequest) {
        Task oldTask = taskRepo.findById(id).orElseThrow(() -> new NotFoundException("Task c ID " + id + " не найден"));
        oldTask.setDescription(taskRequest.getDescription());
        oldTask.setCompleted(taskRequest.isCompleted());
        taskRepo.save(oldTask);
        return TaskResponse.builder().id(oldTask.getId()).description(oldTask.getDescription()).completed(oldTask.isCompleted()).build();
    }

    @Override
    public SimpleResponse deleteTaskById(Long id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new NotFoundException("Task c ID " + id + " не найден"));
        if (task.isCompleted()) {
            taskRepo.delete(task);
            return SimpleResponse.builder().status(HttpStatus.OK).message("Task c ID " + id + " успешно удален").build();
        }
        throw new BadRequestException(" Нельзя удалить не выполненую заданию");
    }
}
