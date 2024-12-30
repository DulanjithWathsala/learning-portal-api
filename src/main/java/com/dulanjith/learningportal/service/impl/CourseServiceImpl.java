package com.dulanjith.learningportal.service.impl;

import com.dulanjith.learningportal.dto.CourseDto;
import com.dulanjith.learningportal.entitiy.Course;
import com.dulanjith.learningportal.repository.CourseRepository;
import com.dulanjith.learningportal.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ObjectMapper mapper;

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public CourseDto addCourse(CourseDto courseDto) {
        Course course = mapper.convertValue(courseDto, Course.class);
        return mapper.convertValue(courseRepository.save(course), CourseDto.class);
    }
}
