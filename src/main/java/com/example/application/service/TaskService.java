package com.example.application.service;

import com.example.application.entity.TaskEntity;
import com.example.application.entity.UserEntity;
import com.example.application.model.Task;
import com.example.application.model.User;
import com.example.application.repository.TaskRepo;
import com.example.application.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для работы с задачами.
 */
@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private UserRepo userRepo;

    /**
     * Создает новую задачу для указанного пользователя.
     * @param task новая задача
     * @param userId идентификатор пользователя
     * @return созданная задача в виде модели
     */
    public Task create(TaskEntity task, long userId) {
        UserEntity user = userRepo.findById(userId).get();
        task.setUser(user);
        return Task.toModel(taskRepo.save(task));
    }

    /**
     * Помечает задачу с указанным идентификатором как завершенную или незавершенную.
     * @param id идентификатор задачи
     * @return обновленная задача в виде модели
     */
    public Task complete(long id) {
        TaskEntity task = taskRepo.findById(id).get();
        task.setCompleted(!task.getCompleted());
        return Task.toModel(taskRepo.save(task));
    }

    /**
     * Помечает задачу с указанным идентификатором как завершенную или незавершенную.
     * @param id идентификатор задачи
     * @return обновленная задача в виде модели
     */
    public List<Task> getAllTasks(long id) {
        UserEntity entity = userRepo.findById(id).get();
        return User.toModel(entity).getTasks();
    }
}
