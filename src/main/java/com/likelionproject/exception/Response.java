package com.likelionproject.exception;

import com.likelionproject.dto.joindto.FailResult;
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
