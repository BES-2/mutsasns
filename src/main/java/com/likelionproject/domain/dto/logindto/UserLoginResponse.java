package com.likelionproject.domain.dto.logindto;

import com.likelionproject.domain.dto.result.LoginResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserLoginResponse {
    private String resultCode;
    private LoginResult result;
}
