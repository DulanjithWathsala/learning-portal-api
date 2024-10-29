package com.dulanjith.learningportal.service;

import com.dulanjith.learningportal.dto.UserDto;
import org.springframework.data.domain.Page;

public interface UserService {

    UserDto register(UserDto userDto);

    UserDto retrieveUserByEmail(String email);

    Page<UserDto> getAllUsers(int page, int size);
}
