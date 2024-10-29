package com.dulanjith.learningportal.mapper;


import com.dulanjith.learningportal.dto.UserDto;
import com.dulanjith.learningportal.entitiy.User;
import com.dulanjith.learningportal.enums.Role;
import com.dulanjith.learningportal.enums.Status;

import java.time.format.DateTimeFormatter;

public class UserMapper {

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private UserMapper() {}

    public static UserDto toDTO(User user) {
        UserDto userDTO = new UserDto();

        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());

        userDTO.setRole(user.getRole().name());
        userDTO.setStatus(user.getStatus().name());

        userDTO.setCreatedAt(user.getCreatedAt() != null
                ? user.getCreatedAt().format(formatter) : null);
        userDTO.setUpdatedAt(user.getUpdatedAt() != null
                ? user.getUpdatedAt().format(formatter) : null);

        return userDTO;
    }

    public static User toEntity(UserDto userDto) {
        User user = new User();

        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        user.setRole(Role.valueOf(userDto.getRole()));
        user.setStatus(Status.valueOf(userDto.getStatus()));

        return user;
    }
}
