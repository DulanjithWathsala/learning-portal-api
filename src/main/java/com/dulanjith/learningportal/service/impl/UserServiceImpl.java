package com.dulanjith.learningportal.service.impl;

import com.dulanjith.learningportal.dto.UserDto;
import com.dulanjith.learningportal.entitiy.Authority;
import com.dulanjith.learningportal.entitiy.User;
import com.dulanjith.learningportal.exception.UserAlreadyExistsException;
import com.dulanjith.learningportal.exception.UserNotFoundException;
import com.dulanjith.learningportal.mapper.UserMapper;
import com.dulanjith.learningportal.repository.UserRepository;
import com.dulanjith.learningportal.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto register(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(
                    "User already exist with the email: " + userDto.getEmail());
        }

        User user = UserMapper.toEntity(userDto);
        user.setAuthorities(Set.of(
                new Authority("ROLE_" + userDto.getRole(), user)));
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return UserMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserDto retrieveUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found for the email: " + email);
        }

        return UserMapper.toDTO(optionalUser.get());
    }

    @Override
    public Page<UserDto> getAllUsers(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size))
                        .map(UserMapper::toDTO);
    }

    @Override
    public Map<String, String> updateEmail(String prevEmail, String newEmail) {
        Optional<User> optionalUser = userRepository.findByEmail(prevEmail);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found for the email: " + prevEmail);
        }

        User user = optionalUser.get();
        user.setEmail(newEmail);
        userRepository.save(user);

        return Collections.singletonMap("Success", "Email updated successfully");
    }


}
