package com.dulanjith.learningportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    private String status;
    private String createdAt;
    private String updatedAt;
}
