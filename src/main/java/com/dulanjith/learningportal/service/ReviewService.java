package com.dulanjith.learningportal.service;

import com.dulanjith.learningportal.dto.ReviewDto;

import java.util.Map;

public interface ReviewService {

    Map<String, String> addReview(ReviewDto reviewDto);
}
