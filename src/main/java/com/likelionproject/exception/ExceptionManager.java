package com.likelionproject.exception;

import com.likelionproject.dto.joindto.FailResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.error(e.getMessage(), new FailResult(e.toString(),e.getMessage())));
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<?> UserExceptionHandler(UserException e) {
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(Response.error("ERROR", new FailResult(e.getErrorCode().toString(), e.getErrorCode().getMessage())));
    }
}