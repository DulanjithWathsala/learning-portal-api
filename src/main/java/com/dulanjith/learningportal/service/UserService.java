package com.dulanjith.learningportal.service;

import com.dulanjith.learningportal.dto.LoginRequestDto;
import com.dulanjith.learningportal.dto.LoginResponseDto;
import com.dulanjith.learningportal.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {

    UserDto register(UserDto userDto);

    UserDto retrieveUserByEmail(String email);

    Page<UserDto> getAllUsers(int page, int size);

    Map<String, String> updateEmail(String prevEmail, String newEmail);

    ResponseEntity<LoginResponseDto> apiLogin(LoginRequestDto loginRequestDto);
}
