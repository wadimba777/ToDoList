package com.example.application.exception.user;

/**
 * Кастомный Exception для случаев если пользователь не найден.
 */
public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message) {
        super(message);
    }
}
