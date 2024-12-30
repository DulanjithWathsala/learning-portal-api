package com.dulanjith.learningportal.service.impl;

import com.dulanjith.learningportal.dto.ReviewDto;
import com.dulanjith.learningportal.entitiy.Course;
import com.dulanjith.learningportal.entitiy.Review;
import com.dulanjith.learningportal.entitiy.User;
import com.dulanjith.learningportal.enums.ResponseCode;
import com.dulanjith.learningportal.exception.ResourceNotFoundException;
import com.dulanjith.learningportal.repository.CourseRepository;
import com.dulanjith.learningportal.repository.ReviewRepository;
import com.dulanjith.learningportal.repository.UserRepository;
import com.dulanjith.learningportal.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Override
    public Map<String, String> addReview(ReviewDto reviewDto) {
        User user = userRepository.findById(reviewDto.getUserId()).orElseThrow(
                () -> new ResourceNotFoundException(ResponseCode.RESOURCE_NOT_FOUND.getMessage()));

        Course course = courseRepository.findById(reviewDto.getCourseId()).orElseThrow(
                () -> new ResourceNotFoundException(ResponseCode.RESOURCE_NOT_FOUND.getMessage()));

        Review review = new Review();
        review.setContent(reviewDto.getContent());
        review.setRating(reviewDto.getRating());
        review.setUser(user);
        review.setCourse(course);

        reviewRepository.save(review);

        return Collections.singletonMap(
                ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }
}
