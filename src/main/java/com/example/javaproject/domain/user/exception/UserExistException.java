package com.example.javaproject.domain.user.exception;

import com.example.javaproject.global.error.exception.CustomException;
import com.example.javaproject.global.error.exception.ErrorCode;

public class UserExistException extends CustomException {
    public static final UserExistException EXCEPTION =
            new UserExistException();

    public UserExistException() {
        super(ErrorCode.USER_EXIST_EXCEPTION);
    }
}
