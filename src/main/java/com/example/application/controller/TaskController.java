package com.example.application.controller;

import com.example.application.entity.TaskEntity;
import com.example.application.exception.task.TaskAlreadyCompletedException;
import com.example.application.exception.task.TaskNotFoundException;
import com.example.application.exception.user.UserNotFoundException;
import com.example.application.model.Task;
import com.example.application.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для работы с задачами.
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskEntity task,
                                           @RequestParam Long userId) {
        return ResponseEntity.ok(taskService.createTask(task, userId));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId) throws TaskNotFoundException {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok("Задача удалена");
    }

    @PutMapping
    public ResponseEntity<Task> completeTask(@RequestParam Long id) throws TaskNotFoundException, TaskAlreadyCompletedException {
        return ResponseEntity.ok(taskService.complete(id));
    }
}
