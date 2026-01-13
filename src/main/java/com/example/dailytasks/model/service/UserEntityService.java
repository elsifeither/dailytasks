package com.example.dailytasks.model.service;

import org.modelmapper.ModelMapper;
import com.example.dailytasks.model.dto.UserRegisterDTO;
import com.example.dailytasks.model.entity.UserEntity;
import com.example.dailytasks.model.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserEntityService {


    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserEntityService(ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public boolean registerUser(UserRegisterDTO userRegisterDTO) {
        Optional<UserEntity> existingUser = userRepository
                .findByEmail(userRegisterDTO.getEmail());

        if (existingUser.isPresent()) {
            return false;
        }

        UserEntity user = map(userRegisterDTO);
        userRepository.save(user);
        return true;
    }



    private UserEntity map(UserRegisterDTO userRegisterDTO) {
        UserEntity mappedEntity = modelMapper.map(userRegisterDTO, UserEntity.class);
        mappedEntity.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        return mappedEntity;
    }
}