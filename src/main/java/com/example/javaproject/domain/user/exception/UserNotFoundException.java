package com.example.javaproject.domain.user.exception;

import com.example.javaproject.global.error.exception.CustomException;
import com.example.javaproject.global.error.exception.ErrorCode;

public class UserNotFoundException extends CustomException {
    public static final UserNotFoundException EXCEPTION =
            new UserNotFoundException();

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
