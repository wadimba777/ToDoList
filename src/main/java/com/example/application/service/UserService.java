package com.example.application.service;

import com.example.application.entity.UserEntity;
import com.example.application.exception.user.UserAlreadyExistException;
import com.example.application.exception.user.UserNotFoundException;
import com.example.application.model.User;
import com.example.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с пользователями.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Регистрирует нового пользователя.
     * @param user новый пользователь
     * @return сохраненная сущность пользователя
     * @throws UserAlreadyExistException если пользователь с таким именем уже существует
     */
    public UserEntity registerUser(UserEntity user) throws UserAlreadyExistException {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistException("Пользователь с таким именем уже существует");
        }
        return userRepository.save(user);
    }

    /**
     * Возвращает пользователя с указанным идентификатором.
     * @param id идентификатор пользователя
     * @return пользователь в виде модели
     * @throws UserNotFoundException если пользователь не найден
     */
    public User getOne(long id) throws UserNotFoundException {
        UserEntity entity = userRepository.findById(id).orElse(null);
        if (entity == null) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        return User.toModel(entity);
    }

    /**
     * Удаляет пользователя с указанным идентификатором.
     * @param id идентификатор пользователя
     * @return идентификатор удаленного пользователя
     */
    public Long deleteUser(Long id) {
        userRepository.deleteById(id);
        return id;
    }
}
