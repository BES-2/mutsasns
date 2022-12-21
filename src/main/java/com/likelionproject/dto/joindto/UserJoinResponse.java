package com.likelionproject.dto.joindto;

import com.likelionproject.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserJoinResponse {
    private String resultCode;
    private JoinResult joinResult;
}
