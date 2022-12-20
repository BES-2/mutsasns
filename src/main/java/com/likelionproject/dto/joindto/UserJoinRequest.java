package com.likelionproject.dto.joindto;

import com.likelionproject.dto.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserJoinRequest {
    private String userName;
    private String password;

    public User toEntity() {
        return User.builder()
                .userName(this.getUserName())
                .password(this.getPassword())
                .build();
    }
}
