package com.likelionproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    DUPLICATED_USERNAME(HttpStatus.CONFLICT, "UserName이 중복됩니다."),
    INVALID_PASSWORD(HttpStatus.CONFLICT, "잘못된 Password입니다."),
    USER_NOT_FOUNDED(HttpStatus.CONFLICT, "유저를 찾을 수 없습니다.");

    private HttpStatus status;
    private String message;
}
