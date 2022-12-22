package com.likelionproject.domain.dto;

import com.likelionproject.domain.dto.result.FailResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Response<T> {
    private String resultCode;
    private T result;


    public static Response<Void> error(String resultCode, FailResult result) {
        return new Response(resultCode, result);
    }

    public static <T> Response<T> success(T result) {
        return new Response("SUCCESS", result);
    }
}
