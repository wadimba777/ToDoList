package com.example.application.model;

import com.example.application.entity.TaskEntity;
import lombok.Data;

/**
 * Модель задачи, используемая для представления задачи в системе.
 */
@Data
public class Task {
    private Long id;
    private String title;
    private Boolean completed;
    private String description;
}
