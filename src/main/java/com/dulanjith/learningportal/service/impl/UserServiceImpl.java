package com.dulanjith.learningportal.service.impl;

import com.dulanjith.learningportal.constant.ApplicationConstants;
import com.dulanjith.learningportal.dto.LoginRequestDto;
import com.dulanjith.learningportal.dto.LoginResponseDto;
import com.dulanjith.learningportal.dto.UserDto;
import com.dulanjith.learningportal.entitiy.Authority;
import com.dulanjith.learningportal.entitiy.User;
import com.dulanjith.learningportal.exception.UserAlreadyExistsException;
import com.dulanjith.learningportal.exception.UserNotFoundException;
import com.dulanjith.learningportal.mapper.UserMapper;
import com.dulanjith.learningportal.repository.UserRepository;
import com.dulanjith.learningportal.service.UserService;
import com.dulanjith.learningportal.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

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

    @Override
    public ResponseEntity<LoginResponseDto> apiLogin(LoginRequestDto loginRequestDto) {
        Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(
                loginRequestDto.username(), loginRequestDto.password());
        Authentication authResponse;
        try {
            authResponse = authenticationManager.authenticate(authentication);
        } catch (Exception ex) {
            throw new BadCredentialsException("Invalid username or password", ex);
        }

        if (authResponse == null || !authResponse.isAuthenticated()) {
            throw new BadCredentialsException("Authentication failed: Invalid username or password");
        }

        String generatedToken = "Bearer " + jwtTokenProvider.generateToken(authResponse);

        return ResponseEntity.status(HttpStatus.OK)
                .header(ApplicationConstants.JWT_HEADER, generatedToken)
                .body(new LoginResponseDto(HttpStatus.OK.getReasonPhrase(), generatedToken));
    }
}
