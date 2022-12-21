package com.likelionproject.exception;

import com.likelionproject.dto.joindto.FailResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserException extends RuntimeException {
    private ErrorCode errorCode;

    public String toString() {
        return String.format("%s, %s", errorCode.toString());
    }

}
