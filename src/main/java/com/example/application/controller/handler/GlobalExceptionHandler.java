package com.example.application.controller.handler;

import com.example.application.exception.task.TaskAlreadyCompletedException;
import com.example.application.exception.user.UserAlreadyExistException;
import com.example.application.exception.task.TaskNotFoundException;
import com.example.application.exception.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Глобальный обработчик исключений для контроллеров.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Обрабатывает исключение UserAlreadyExistException.
     * @param ex исключение
     * @param request запрос
     * @return ответ с сообщением об ошибке
     */
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<String> handleUserAlreadyExistException(UserAlreadyExistException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    /**
     * Обрабатывает исключение UserNotFoundException.
     * @param ex исключение
     * @return ответ с сообщением об ошибке
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Обрабатывает исключение TaskNotFoundException.
     * @param ex исключение
     * @return ответ с сообщением об ошибке
     */
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<String> handleTaskNotFoundException(TaskNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Обрабатывает исключение TaskAlreadyCompletedException.
     * @param ex исключение
     * @return ответ с сообщением об ошибке
     */
    @ExceptionHandler(TaskAlreadyCompletedException.class)
    public ResponseEntity<String> handleTaskAlreadyCompletedException(TaskAlreadyCompletedException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    /**
     * Обрабатывает все остальные исключения.
     * @param ex исключение
     * @return ответ с сообщением об ошибке
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
