package com.example.application.model;

import com.example.application.entity.UserEntity;
import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;

    public static User toModel(UserEntity entity) {
        User model = new User();
        model.setId(entity.getId());
        model.setUsername(entity.getUsername());
        return model;
    }
}
