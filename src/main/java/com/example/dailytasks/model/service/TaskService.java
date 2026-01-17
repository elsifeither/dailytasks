package com.example.dailytasks.model.service;

import com.example.dailytasks.model.dto.TaskDTO;
import com.example.dailytasks.model.entity.Task;
import com.example.dailytasks.model.entity.UserEntity;
import com.example.dailytasks.model.repository.TaskRepository;
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


}