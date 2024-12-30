package com.dulanjith.learningportal.controller;

import com.dulanjith.learningportal.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
}
