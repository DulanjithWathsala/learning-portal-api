package com.dulanjith.learningportal.service.impl;

import com.dulanjith.learningportal.repository.CourseRepository;
import com.dulanjith.learningportal.repository.ReviewRepository;
import com.dulanjith.learningportal.repository.UserRepository;
import com.dulanjith.learningportal.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
}
