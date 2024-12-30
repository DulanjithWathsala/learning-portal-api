package com.dulanjith.learningportal.controller;

import com.dulanjith.learningportal.dto.ReviewDto;
import com.dulanjith.learningportal.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Map<String, String>> addReview(@RequestBody ReviewDto reviewDto) {
        return ResponseEntity.ok(reviewService.addReview(reviewDto));
    }
}
