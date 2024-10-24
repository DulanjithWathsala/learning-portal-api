package com.dulanjith.learningportal.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Builder
@Setter
@Getter
public class ErrorResponse {

    private HttpStatus status;
    private String message;
}
