package com.example.application.controller;

import com.example.application.entity.TaskEntity;
import com.example.application.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    public static final String ERROR = "Произошла ошибка";

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity createTask(@RequestBody TaskEntity task,
                                     @RequestParam long userId) {
        try {
            return ResponseEntity.ok(taskService.create(task, userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ERROR);
        }
    }

    @PutMapping
    public ResponseEntity completeTask(@RequestParam long id) {
        try {
            return ResponseEntity.ok(taskService.complete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ERROR);
        }
    }
}
