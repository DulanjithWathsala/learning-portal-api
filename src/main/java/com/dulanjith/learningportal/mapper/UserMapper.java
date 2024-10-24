package com.dulanjith.learningportal.mapper;


import com.dulanjith.learningportal.dto.UserDTO;
import com.dulanjith.learningportal.entitiy.User;
import com.dulanjith.learningportal.enums.Role;
import com.dulanjith.learningportal.enums.Status;

import java.time.format.DateTimeFormatter;

public class UserMapper {

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private UserMapper() {}

    public static UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();

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

    public static User toEntity(UserDTO userDTO) {
        User user = new User();

        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());

        user.setRole(Role.valueOf(userDTO.getRole()));
        user.setStatus(Status.valueOf(userDTO.getStatus()));

        return user;
    }
}
