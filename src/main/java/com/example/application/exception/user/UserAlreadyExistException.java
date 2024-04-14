package com.example.application.exception.user;

/**
 * Кастомный Exception для случаев если пользователь уже существует.
 */
public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
