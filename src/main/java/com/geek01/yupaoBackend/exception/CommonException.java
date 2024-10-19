package com.geek01.yupaoBackend.exception;

import lombok.Getter;

@Getter
public class CommonException extends RuntimeException {
    private int code;

    public CommonException(int code, String message) {
        super(message);
        this.code = code;
    }

    public CommonException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public CommonException(int code, Throwable cause) {
        super(cause);
        this.code = code;
    }
}
