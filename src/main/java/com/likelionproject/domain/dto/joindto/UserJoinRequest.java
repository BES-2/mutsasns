package com.likelionproject.domain.dto.joindto;

import com.likelionproject.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserJoinRequest {
    private String userName;
    private String password;

    public User toEntity(String password) {
        return User.builder()
                .userName(this.getUserName())
                .password(password)
                .build();
    }
}
