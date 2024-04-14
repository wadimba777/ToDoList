package com.example.application.controller;

import com.example.application.entity.UserEntity;
import com.example.application.exception.user.UserAlreadyExistException;
import com.example.application.exception.user.UserNotFoundException;
import com.example.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    public static final String ERROR = "Произошла ошибка";

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity getUsers() {
        try {
            return ResponseEntity.ok("Сервер работает!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ERROR);
        }
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.deleteUser(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ERROR);
        }
    }

}
