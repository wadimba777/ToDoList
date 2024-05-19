package com.example.application.repository;

import com.example.application.entity.TaskEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Интерфейс репозитория для доступа к задачам в базе данных.
 */
public interface TaskRepository extends CrudRepository<TaskEntity, Long> {
}