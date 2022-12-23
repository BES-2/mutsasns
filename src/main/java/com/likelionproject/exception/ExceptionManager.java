package com.likelionproject.exception;

import com.likelionproject.domain.dto.result.FailResult;
import com.likelionproject.domain.dto.Response;
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

    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> UserExceptionHandler(AppException e) {
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(Response.error("ERROR", new FailResult(e.getErrorCode().toString(), e.getErrorCode().getMessage())));
    }
}