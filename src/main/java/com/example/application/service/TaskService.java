package com.example.application.service;

import com.example.application.entity.TaskEntity;
import com.example.application.entity.UserEntity;
import com.example.application.exception.task.TaskAlreadyCompletedException;
import com.example.application.exception.task.TaskNotFoundException;
import com.example.application.model.Task;
import com.example.application.repository.TaskRepository;
import com.example.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с задачами.
 */
@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    /**
     * Создает новую задачу для указанного пользователя.
     * @param task новая задача
     * @param userId идентификатор пользователя
     * @return созданная задача в виде модели
     */
    public Task create(TaskEntity task, Long userId) {
        UserEntity user = userRepository.findById(userId).get();
        task.setUser(user);
        return Task.toModel(taskRepository.save(task));
    }

    /**
     * Помечает задачу с указанным идентификатором как завершенную.
     * @param id идентификатор задачи
     * @return обновленная задача в виде модели
     */
    public Task complete(Long id) throws TaskNotFoundException, TaskAlreadyCompletedException {
        TaskEntity task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Задача с таким ID не найдена"));
        if (task.getCompleted()) {
            throw new TaskAlreadyCompletedException("Задача уже помечена как выполненная");
        }
        task.setCompleted(true);
        return Task.toModel(taskRepository.save(task));
    }
}
