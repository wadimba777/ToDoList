package com.example.application.repository;

import com.example.application.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Интерфейс репозитория для доступа к пользователям в базе данных.
 */
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
