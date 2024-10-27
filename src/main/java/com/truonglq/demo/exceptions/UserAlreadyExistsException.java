package com.truonglq.demo.exceptions;

import lombok.Getter;

@Getter
public class UserAlreadyExistsException extends EntityAlreadyExistsException {
    public UserAlreadyExistsException() {
        super("User with this username already exists.");
    }
}
