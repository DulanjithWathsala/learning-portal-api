package com.dulanjith.learningportal.controller;

import com.dulanjith.learningportal.dto.CourseDto;
import com.dulanjith.learningportal.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/new")
    public ResponseEntity<CourseDto> addCourse(@RequestBody CourseDto courseDto) {
        return ResponseEntity.ok(courseService.addCourse(courseDto));
    }
}
