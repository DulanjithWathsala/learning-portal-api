package com.dulanjith.learningportal.controller;

import com.dulanjith.learningportal.constant.ApplicationConstants;
import com.dulanjith.learningportal.dto.LoginRequestDto;
import com.dulanjith.learningportal.dto.LoginResponseDto;
import com.dulanjith.learningportal.dto.UserDto;
import com.dulanjith.learningportal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @GetMapping("/all")
    public ResponseEntity<Page<UserDto>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(userService.getAllUsers(page, size));
    }

    @PatchMapping("/update-email")
    public ResponseEntity<Map<String, String>> updateEmail(
             @RequestParam String prevEmail,
             @RequestParam String newEmail) {
        return ResponseEntity.ok(userService.updateEmail(prevEmail, newEmail));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginApi(
            @RequestBody LoginRequestDto loginRequestDto) {
        return userService.apiLogin(loginRequestDto);
    }
}
