package com.example.application.service;

import com.example.application.entity.UserEntity;
import com.example.application.exception.UserAlreadyExistException;
import com.example.application.exception.UserNotFoundException;
import com.example.application.model.User;
import com.example.application.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistException("Пользователь с таким именем уже существует");
        }
        return userRepo.save(user);
    }

    public User getOne(long id) throws UserNotFoundException {
        UserEntity entity = userRepo.findById(id).get();
        if (entity == null) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        return User.toModel(entity);
    }

    public Long deleteUser(Long id) {
        userRepo.deleteById(id);
        return id;
    }
}
