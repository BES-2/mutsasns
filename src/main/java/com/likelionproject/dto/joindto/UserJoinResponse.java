package com.likelionproject.dto.joindto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserJoinResponse {
    private String resultCode;
    private Result result;
}
