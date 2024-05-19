package com.example.application.exception.task;

/**
 * Кастомный Exception для случаев если задача уже выполнена.
 */
public class TaskAlreadyCompletedException extends Exception{
    public TaskAlreadyCompletedException(String message) {
        super(message);
    }
}
