package com.example.application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Сущность, представляющая собой пользователя.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    /**
     * Уникальный идентификатор пользователя.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Имя пользователя.
     */
    @Column(name = "username")
    private String username;

    /**
     * Пароль пользователя.
     */
    @Column(name = "password")
    private String password;

    /**
     * Список задач, принадлежащих пользователю.
     * <p>
     * Маппинг выполняется через поле "user" в сущности TaskEntity.
     * Каскадное удаление задач при удалении пользователя.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<TaskEntity> tasks;
}
