package com.dulanjith.learningportal.service.impl;

import com.dulanjith.learningportal.dto.CourseDto;
import com.dulanjith.learningportal.entitiy.Course;
import com.dulanjith.learningportal.repository.CourseRepository;
import com.dulanjith.learningportal.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ObjectMapper mapper;

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public CourseDto add(CourseDto courseDto) {
        Course course = mapper.convertValue(courseDto, Course.class);
        return mapper.convertValue(courseRepository.save(course), CourseDto.class);
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public List<CourseDto> retrieveAll() {
        List<CourseDto> courseDtoList = new ArrayList<>();
        courseRepository.findAll().forEach(
                course -> courseDtoList.add(mapper.convertValue(course, CourseDto.class)));

        return courseDtoList;
    }


}
