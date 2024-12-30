package com.dulanjith.learningportal.enums;

import lombok.Getter;

@Getter
public enum ResponseCode {

    SUCCESS("200", "Operation successful"),
    INTERNAL_SERVER_ERROR("500", "Internal Server Error"),
    USER_NOT_FOUND("404", "User not found"),
    USER_ALREADY_EXIST("400", "User already exist for the given information"),
    RESOURCE_NOT_FOUND("404", "Requested resource not found"),
    BAD_CREDENTIALS("401", "Invalid username or password");

    private final String code;
    private final String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
