package com.example.application.service;

import com.example.application.entity.UserEntity;
import com.example.application.exception.user.UserAlreadyExistException;
import com.example.application.exception.user.UserNotFoundException;
import com.example.application.model.User;
import com.example.application.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервис для работы с пользователями.
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
     */
    public Optional<User> getOne(Long id) {
        return userRepository.findById(id).map(UserService::toModel);
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

    /**
     * Конвертирует класс Entity в Model.
     * @param entity обьект UserEntity
     * @return обьект модели класса User
     */
    public static User toModel(UserEntity entity) {
        User model = new User();
        model.setId(entity.getId());
        model.setUsername(entity.getUsername());
        model.setTasks(entity.getTasks()
                .stream()
                .map(TaskService::toModel)
                .collect(Collectors.toList()));
        return model;
    }
}
