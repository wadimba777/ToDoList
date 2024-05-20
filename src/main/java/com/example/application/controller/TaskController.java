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

    /**
     * Создает новую задачу для указанного пользователя.
     * @param task новая задача
     * @param userId идентификатор пользователя
     * @return созданная задача в виде ответа или сообщение об ошибке
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskEntity task,
                                           @RequestParam Long userId) {
        return ResponseEntity.ok(taskService.createTask(task, userId));
    }

    /**
     * Удаляет задачу с указанным id.
     * @param taskId идентификатор задачи
     * @return ответ с сообщением об удалении или сообщением об ошибке
     */
    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId) throws TaskNotFoundException {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok("Задача удалена");
    }

    /**
     * Помечает задачу с указанным идентификатором как завершенную.
     * @param id идентификатор задачи
     * @return ответ с завершенной задачей или сообщение об ошибке
     */
    @PutMapping
    public ResponseEntity<Task> completeTask(@RequestParam Long id) throws TaskNotFoundException, TaskAlreadyCompletedException {
        return ResponseEntity.ok(taskService.complete(id));
    }
}
