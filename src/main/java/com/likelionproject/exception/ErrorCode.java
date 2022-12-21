package com.likelionproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    DUPLICATED_USERNAME(HttpStatus.CONFLICT, "UserName이 중복됩니다.");

    private HttpStatus status;
    private String message;
}
