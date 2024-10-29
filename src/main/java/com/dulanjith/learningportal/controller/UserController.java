package com.dulanjith.learningportal.controller;

import com.dulanjith.learningportal.dto.UserDto;
import com.dulanjith.learningportal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public UserDto register(@RequestBody UserDto userDto) {
        return userService.register(userDto);
    }

    @GetMapping("/by-email")
    public UserDto retrieveUserByEmail(@RequestParam String email) {
        return userService.retrieveUserByEmail(email);
    }
}
