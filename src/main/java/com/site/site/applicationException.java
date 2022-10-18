package com.site.site;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "entity not found")
public class applicationException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public applicationException(String message) {
        super(message);
    }
}