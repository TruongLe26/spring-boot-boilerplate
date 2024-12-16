package com.truonglq.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error"),
    INVALID_KEY(1001, "Invalid message key"),
    USER_EXISTED(1002, "User existed"),
    USERNAME_INVALID(1003, "Username must be at least 3 characters"),
    INVALID_PASSWORD(1004, "Password must be at least 8 characters"),
    USER_NOT_EXISTED(1005, "User not existed"),
    ROLE_NOT_FOUND(1006, "Role not found"),
    UNAUTHENTICATED(401, "Invalid username or password"),
    UNAUTHORIZED(403, "Access is denied")
    ;

    private int code;
    private String message;
}