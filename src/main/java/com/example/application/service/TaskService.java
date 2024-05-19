package com.example.application.service;

import com.example.application.entity.TaskEntity;
import com.example.application.entity.UserEntity;
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

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Создает новую задачу для указанного пользователя.
     * @param task новая задача
     * @param userId идентификатор пользователя
     * @return созданная задача в виде модели
     */
    public Task create(TaskEntity task, long userId) {
        UserEntity user = userRepository.findById(userId).get();
        task.setUser(user);
        return Task.toModel(taskRepository.save(task));
    }

    /**
     * Помечает задачу с указанным идентификатором как завершенную или незавершенную.
     * @param id идентификатор задачи
     * @return обновленная задача в виде модели
     */
    public Task complete(long id) {
        TaskEntity task = taskRepository.findById(id).get();
        task.setCompleted(!task.getCompleted());
        return Task.toModel(taskRepository.save(task));
    }
}
