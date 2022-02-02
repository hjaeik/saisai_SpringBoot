package com.saisai.web.exception;

import lombok.Getter;

@Getter
public class SaisaiException extends RuntimeException{
    private ErrorCode errorCode;

    public SaisaiException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
