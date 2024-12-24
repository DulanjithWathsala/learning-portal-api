package com.dulanjith.learningportal.service.impl;

import com.dulanjith.learningportal.dto.ProfileDto;
import com.dulanjith.learningportal.entitiy.Profile;
import com.dulanjith.learningportal.exception.UserNotFoundException;
import com.dulanjith.learningportal.repository.ProfileRepository;
import com.dulanjith.learningportal.service.ProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ObjectMapper objectMapper;

    @Override
    public ProfileDto updateProfilePicture(String profilePictureUrl, String email) {
        Profile profile = profileRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found for the given email"));

        profile.setProfilePictureUrl(profilePictureUrl);

        return objectMapper.convertValue(profileRepository.save(profile), ProfileDto.class);
    }

    @Override
    public ProfileDto retrieve(String email) {
        return objectMapper.convertValue(
                profileRepository.findByEmail(email).orElseThrow(() ->
                        new UserNotFoundException("User not found for the given email")),
                ProfileDto.class);
    }
}
