package com.example.dailytasks.service;

import com.example.dailytasks.model.dto.TaskDTO;
import com.example.dailytasks.model.entity.Task;
import com.example.dailytasks.model.entity.UserEntity;
import com.example.dailytasks.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskService {

    private final ModelMapper modelMapper;
    private final TaskRepository taskRepository;

    public TaskService(ModelMapper modelMapper, TaskRepository taskRepository) {
        this.modelMapper = modelMapper;
        this.taskRepository = taskRepository;
    }

    public void saveTask(TaskDTO taskDTO,UserEntity user) {
        Task task = modelMapper.map(taskDTO, Task.class);
        task.setUser(user);
        taskRepository.save(task);
    }

    public List<Task> getTasksForUser(UserEntity user) {
        return taskRepository.findAllByUserEntity(user);
    }

    public void deleteTask(Long taskId, UserEntity user) {
        Task task = taskRepository.findByIdAndUserEntity(taskId, user)
                .orElseThrow(() -> new IllegalArgumentException("Task not found or you are not the owner"));
        taskRepository.delete(task);
    }









}