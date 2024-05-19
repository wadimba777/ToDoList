package com.example.application.exception.task;

/**
 * Кастомный Exception для случаев если задача не найдена.
 */
public class TaskNotFoundException extends Exception {
    public TaskNotFoundException(String message) {
        super(message);
    }
}
