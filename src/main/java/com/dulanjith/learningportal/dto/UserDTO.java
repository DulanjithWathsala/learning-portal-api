package com.dulanjith.learningportal.dto;

import com.dulanjith.learningportal.enums.Role;
import com.dulanjith.learningportal.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private Status status;
    private String createdAt;
    private String updatedAt;
}
