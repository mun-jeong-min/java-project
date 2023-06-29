package com.example.javaproject.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    INVALID_JWT(401, "jwt-401-1", "jwt 인증 실패"),
    EXPIRED_JWT(401, "jwt-401-2", "jwt 기간 만료"),

    USER_NOT_FOUND(404, "user-404-1", "유저를 찾을 수 없음"),

    USER_EXIST_EXCEPTION(409, "user-409-1", "유저가 이미 존재함");

    private final int status;
    private final String code;
    private final String message;
}
