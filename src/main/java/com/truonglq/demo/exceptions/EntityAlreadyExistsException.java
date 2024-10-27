package com.truonglq.demo.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EntityAlreadyExistsException extends RuntimeException {

    private final int statusCode;

    public EntityAlreadyExistsException(String message) {
        super(message);
        this.statusCode = HttpStatus.CONFLICT.value();
    }

}
