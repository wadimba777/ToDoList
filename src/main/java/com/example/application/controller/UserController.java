package com.example.application.controller;

import com.example.application.entity.TaskEntity;
import com.example.application.entity.UserEntity;
import com.example.application.exception.user.UserAlreadyExistException;
import com.example.application.exception.user.UserNotFoundException;
import com.example.application.model.Task;
import com.example.application.repository.UserRepo;
import com.example.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для работы с пользователями.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * Сообщение об ошибке по умолчанию.
     */
    public static final String ERROR = "Произошла ошибка";

    @Autowired
    private UserService userService;

    /**
     * Получает список пользователей.
     * @return ответ с сообщением "Сервер работает!"
     */
    @GetMapping("/")
    public ResponseEntity getUsers() {
        try {
            return ResponseEntity.ok("Сервер работает!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ERROR);
        }
    }

    /**
     * Регистрирует нового пользователя.
     * @param user новый пользователь
     * @return ответ с сообщением об успешной регистрации или ошибкой
     */
    @PostMapping
    public ResponseEntity registerUser(@RequestBody UserEntity user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.ok("Пользователь успешно добавлен!");
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ERROR);
        }
    }

    /**
     * Получает информацию о пользователе по его идентификатору.
     * @param id идентификатор пользователя
     * @return пользователь в виде ответа или сообщение об ошибке
     */
    @GetMapping
    public ResponseEntity getUser(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(userService.getOne(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ERROR);
        }
    }
//
//    /**
//     * Получает информацию о всех задачах пользователя по его идентификатору.
//     * @param id идентификатор пользователя
//     * @return список задач в виде ответа или сообщение об ошибке
//     */
//    @GetMapping("/{id}/tasks")
//    public ResponseEntity getAllUserTasks(@PathVariable long id) {
//        try {
//            List<Task> tasks = userService.getAllTasks(id);
//            return ResponseEntity.ok(tasks);
//        } catch (UserNotFoundException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(ERROR);
//        }
//    }

    /**
     * Удаляет пользователя по его идентификатору.
     * @param id идентификатор пользователя для удаления
     * @return ответ с идентификатором удаленного пользователя или сообщение об ошибке
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.deleteUser(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ERROR);
        }
    }

}
