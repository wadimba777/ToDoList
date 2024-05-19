package com.example.application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность, представляющая собой задачу в системе.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class TaskEntity {
    /**
     * Уникальный идентификатор задачи.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Заголовок задачи.
     */
    @Column(name = "title")
    private String title;

    /**
     * Описание задачи.
     */
    @Column(name = "description")
    private String description;

    /**
     * Статус выполнения задачи (true - завершена, false - не завершена).
     */
    @Column(name = "completed")
    private Boolean completed;

    /**
     * Пользователь, которому принадлежит задача.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
