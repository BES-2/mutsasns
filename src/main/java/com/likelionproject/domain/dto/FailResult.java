package com.likelionproject.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FailResult {
    private String errorCode;
    private String message;
}
