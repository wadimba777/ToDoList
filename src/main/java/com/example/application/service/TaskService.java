package com.example.application.service;

import com.example.application.entity.TaskEntity;
import com.example.application.entity.UserEntity;
import com.example.application.model.Task;
import com.example.application.repository.TaskRepo;
import com.example.application.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private UserRepo userRepo;

    public Task create(TaskEntity task, long userId) {
        UserEntity user = userRepo.findById(userId).get();
        task.setUser(user);
        return Task.toModel(taskRepo.save(task));
    }

    public Task complete(long id) {
        TaskEntity task = taskRepo.findById(id).get();
        task.setCompleted(!task.getCompleted());
        return Task.toModel(taskRepo.save(task));
    }
}
