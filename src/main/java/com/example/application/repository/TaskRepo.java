package com.example.application.repository;

import com.example.application.entity.TaskEntity;
import com.example.application.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepo extends CrudRepository<TaskEntity, Long> {
}