package com.saisai.web.exception;

import lombok.Getter;

@Getter
public class ExternalException extends RuntimeException{
    private ErrorCode errorCode;
    private String message;

    public ExternalException(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
