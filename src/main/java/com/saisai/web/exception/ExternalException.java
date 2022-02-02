package com.saisai.web.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExternalException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public ExternalException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
