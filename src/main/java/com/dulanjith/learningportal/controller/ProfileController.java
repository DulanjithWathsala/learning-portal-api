package com.dulanjith.learningportal.controller;

import com.dulanjith.learningportal.dto.ProfileDto;
import com.dulanjith.learningportal.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    @PatchMapping("/update-profile-picture")
    public ResponseEntity<ProfileDto> updateProfilePicture(
            @RequestParam String profilePictureUrl, @RequestParam String email) {
        return ResponseEntity.ok(
                profileService.updateProfilePicture(profilePictureUrl, email));
    }

    @GetMapping("/my-profile")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProfileDto> retrieve(@RequestParam String email) {
        return ResponseEntity.ok(profileService.retrieve(email));
    }
}
