package com.dulanjith.learningportal.entitiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;

    private String title;
    private String description;

    @OneToMany(mappedBy = "course")
    private Set<Review> reviews;

    @ManyToMany(mappedBy = "courses")
    private Set<User> users;
}
