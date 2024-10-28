package com.geek01.yupaoBackend.exception;

import com.geek01.yupaoBackend.common.ErrorCode;
import lombok.Getter;

@Getter
public class ErrorException extends RuntimeException {
    private final int code;

    private final String description;

    public ErrorException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public ErrorException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public ErrorException(ErrorCode errorCode, String description) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }
}
