package com.example.javaproject.global.exception;

import com.example.javaproject.global.error.exception.CustomException;
import com.example.javaproject.global.error.exception.ErrorCode;

public class ExpiredJwtException extends CustomException {
    public static final ExpiredJwtException EXCEPTION =
            new ExpiredJwtException();

    public ExpiredJwtException() {
        super(ErrorCode.EXPIRED_JWT);
    }
}
