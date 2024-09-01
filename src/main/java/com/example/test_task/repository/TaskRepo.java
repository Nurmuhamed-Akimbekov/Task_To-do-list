package com.example.test_task.repository;

import com.example.test_task.dto.TaskResponse;
import com.example.test_task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
    @Query("SELECT new com.example.test_task.dto.TaskResponse(t.id, t.description, t.completed) " +
            "FROM Task t")
    List<TaskResponse> getAllTasks();
}
