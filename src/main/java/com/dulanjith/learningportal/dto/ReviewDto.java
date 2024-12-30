package com.dulanjith.learningportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private String content;
    private int rating;
    private Long userId;
    private Long courseId;
}
