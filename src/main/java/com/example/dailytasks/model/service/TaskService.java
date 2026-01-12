package com.example.dailytasks.model.service;

import com.example.dailytasks.model.entity.Task;
import com.example.dailytasks.model.entity.UserEntity;
import com.example.dailytasks.model.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Създаване или обновяване на задача
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    // Връща всички задачи за потребител
    public List<Task> getTasksByUser(UserEntity userEntity) {
        return taskRepository.findByUser(userEntity);
    }

    // Намиране на задача по ID
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    // Изтриване на задача
    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }
}