package com.example.javaproject.global.exception;

import com.example.javaproject.global.error.exception.CustomException;
import com.example.javaproject.global.error.exception.ErrorCode;

public class InvalidJwtException extends CustomException {
    public static final InvalidJwtException EXCEPTION =
            new InvalidJwtException();

    public InvalidJwtException() {
        super(ErrorCode.INVALID_JWT);
    }
}
