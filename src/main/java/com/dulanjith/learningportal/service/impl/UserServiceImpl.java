package com.dulanjith.learningportal.service.impl;

import com.dulanjith.learningportal.dto.UserDto;
import com.dulanjith.learningportal.entitiy.User;
import com.dulanjith.learningportal.exception.UserAlreadyExistsException;
import com.dulanjith.learningportal.exception.UserNotFoundException;
import com.dulanjith.learningportal.mapper.UserMapper;
import com.dulanjith.learningportal.repository.UserRepository;
import com.dulanjith.learningportal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

        return UserMapper.toDTO(
                userRepository.save(UserMapper.toEntity(userDto))
        );
    }

    @Override
    public UserDto retrieveUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found for the email: " + email);
        }

        return UserMapper.toDTO(optionalUser.get());
    }


}
