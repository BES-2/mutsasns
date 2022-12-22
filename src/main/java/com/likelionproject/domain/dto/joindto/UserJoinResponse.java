package com.likelionproject.domain.dto.joindto;

import com.likelionproject.domain.dto.result.JoinResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserJoinResponse {
    private String resultCode;
    private JoinResult result;
}
