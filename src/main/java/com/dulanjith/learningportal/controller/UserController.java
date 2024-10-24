package com.dulanjith.learningportal.controller;

import com.dulanjith.learningportal.dto.UserDTO;
import com.dulanjith.learningportal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDTO register(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }
}
