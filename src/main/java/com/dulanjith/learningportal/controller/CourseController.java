package com.dulanjith.learningportal.controller;

import com.dulanjith.learningportal.dto.CourseDto;
import com.dulanjith.learningportal.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/new")
    public ResponseEntity<CourseDto> add(@RequestBody CourseDto courseDto) {
        return ResponseEntity.ok(courseService.add(courseDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CourseDto>> retrieveAll() {
        return ResponseEntity.ok(courseService.retrieveAll());
    }
}
