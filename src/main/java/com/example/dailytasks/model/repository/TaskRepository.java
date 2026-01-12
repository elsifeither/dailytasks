package com.example.dailytasks.model.repository;

import com.example.dailytasks.model.entity.Task;
import com.example.dailytasks.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser(UserEntity userEntity);


}
