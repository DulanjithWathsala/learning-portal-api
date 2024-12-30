package com.dulanjith.learningportal.service;

import com.dulanjith.learningportal.dto.CourseDto;

import java.util.List;

public interface CourseService {

    CourseDto add(CourseDto courseDto);

    List<CourseDto> retrieveAll();
}
