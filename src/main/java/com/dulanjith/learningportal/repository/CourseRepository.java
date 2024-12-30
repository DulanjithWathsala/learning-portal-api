package com.dulanjith.learningportal.repository;

import com.dulanjith.learningportal.entitiy.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
}
