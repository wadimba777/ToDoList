package com.example.application.controller;

import com.example.application.entity.TaskEntity;
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

    /**
     * Сообщение об ошибке по умолчанию.
     */
    public static final String ERROR = "Произошла ошибка";

    @Autowired
    private TaskService taskService;

    /**
     * Создает новую задачу для указанного пользователя.
     * @param task новая задача
     * @param userId идентификатор пользователя
     * @return созданная задача в виде ответа или сообщение об ошибке
     */
    @PostMapping
    public ResponseEntity createTask(@RequestBody TaskEntity task,
                                     @RequestParam long userId) {
        try {
            return ResponseEntity.ok(taskService.create(task, userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ERROR);
        }
    }

    /**
     * Помечает задачу с указанным идентификатором как завершенную.
     * @param id идентификатор задачи
     * @return ответ с завершенной задачей или сообщение об ошибке
     */
    @PutMapping
    public ResponseEntity completeTask(@RequestParam long id) {
        try {
            return ResponseEntity.ok(taskService.complete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ERROR);
        }
    }
}
