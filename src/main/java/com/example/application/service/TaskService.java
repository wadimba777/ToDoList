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
    public Task createTask(TaskEntity task, Long userId) {
        UserEntity user = userRepository.findById(userId).get();
        task.setUser(user);
        return toModel(taskRepository.save(task));
    }

    /**
     * Удаляет задачу .
     * @param taskId идентификатор задачи
     */
    public void deleteTask(Long taskId) throws TaskNotFoundException {
        TaskEntity task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Задача с таким ID не найдена"));
        taskRepository.delete(task);
    }

    /**
     * Помечает задачу с указанным идентификатором как завершенную.
     * @param taskId идентификатор задачи
     * @return обновленная задача в виде модели
     */
    public Task complete(Long taskId) throws TaskNotFoundException, TaskAlreadyCompletedException {
        TaskEntity task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Задача с таким ID не найдена"));
        if (task.getCompleted()) {
            throw new TaskAlreadyCompletedException("Задача уже помечена как выполненная");
        }
        task.setCompleted(true);
        return toModel(taskRepository.save(task));
    }

    /**
     * Конвертирует класс Entity в Model.
     * @param entity обьект TaskEntity
     * @return обьект модели класса Task
     */
    public static Task toModel(TaskEntity entity) {
        Task model = new Task();
        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        model.setCompleted(entity.getCompleted());
        return model;
    }
}
