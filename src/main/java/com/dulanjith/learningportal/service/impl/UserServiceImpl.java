package com.dulanjith.learningportal.service.impl;

import com.dulanjith.learningportal.dto.UserDto;
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
    public UserDto register(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(
                    "User already exists with the email: " + userDto.getEmail());
        }

        User entity = UserMapper.toEntity(userDto);

        User saved = userRepository.save(entity);

        return UserMapper.toDTO(saved);
    }

    @Override
    public UserDto update(UserDto userDto) {
        return null;
    }
}
