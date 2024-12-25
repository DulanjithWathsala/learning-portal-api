package com.dulanjith.learningportal.service;

import com.dulanjith.learningportal.dto.ProfileDto;

public interface ProfileService {

    ProfileDto updateProfilePicture(String profilePictureUrl, String email);

    ProfileDto retrieve(String email);
}
