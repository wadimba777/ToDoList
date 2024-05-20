package com.example.application.controller;

import com.example.application.entity.UserEntity;
import com.example.application.exception.user.UserAlreadyExistException;
import com.example.application.exception.user.UserNotFoundException;
import com.example.application.model.User;
import com.example.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Контроллер для работы с пользователями.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public Iterable<UserEntity> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody UserEntity user) throws UserAlreadyExistException {
        userService.registerUser(user);
        return ResponseEntity.ok("Пользователь успешно добавлен!");
    }

    @GetMapping
    public ResponseEntity<Optional<User>> getUser(@RequestParam Long id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(id);
    }
}
