package com.dulanjith.learningportal.service.impl;

import com.dulanjith.learningportal.dto.UserDTO;
import com.dulanjith.learningportal.entitiy.User;
import com.dulanjith.learningportal.exception.UserAlreadyExistsException;
import com.dulanjith.learningportal.mapper.UserMapper;
import com.dulanjith.learningportal.repository.UserRepository;
import com.dulanjith.learningportal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO register(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(
                    "User already exists with the email: " + userDTO.getEmail());
        }

        User entity = UserMapper.toEntity(userDTO);

        User saved = userRepository.save(entity);

        return UserMapper.toDTO(saved);
    }
}
