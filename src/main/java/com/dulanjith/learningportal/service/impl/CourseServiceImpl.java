package com.dulanjith.learningportal.service.impl;

import com.dulanjith.learningportal.repository.CourseRepository;
import com.dulanjith.learningportal.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
}
