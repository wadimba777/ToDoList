package com.example.application.model;

import com.example.application.entity.TaskEntity;
import lombok.Data;

@Data
public class Task {
    private Long id;
    private String title;
    private Boolean completed;

    public static Task toModel(TaskEntity entity) {
        Task model = new Task();
        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        model.setCompleted(entity.getCompleted());
        return model;
    }
}
