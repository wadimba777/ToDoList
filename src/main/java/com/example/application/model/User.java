package com.example.application.model;

import com.example.application.entity.UserEntity;
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

    public static User toModel(UserEntity entity) {
        User model = new User();
        model.setId(entity.getId());
        model.setUsername(entity.getUsername());
        model.setTasks(entity.getTasks()
                .stream()
                .map(Task::toModel)
                .collect(Collectors.toList()));
        return model;
    }
}
