package com.dulanjith.learningportal.service;

import com.dulanjith.learningportal.dto.UserDto;

public interface UserService {

    UserDto register(UserDto userDto);

    UserDto update(UserDto userDto);
}
