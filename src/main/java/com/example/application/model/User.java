package com.example.application.model;

import com.example.application.entity.UserEntity;
import com.example.application.service.TaskService;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Модель пользователя, используемая для представления пользователя в системе.
 */
@Data
public class User {
    private Long id;
    private String username;
    private List<Task> tasks;
}
