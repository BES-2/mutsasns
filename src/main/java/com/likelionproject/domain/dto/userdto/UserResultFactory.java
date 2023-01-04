package com.likelionproject.domain.dto.userdto;

import com.likelionproject.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class UserResultFactory {

    public static JoinResult from(User user) {
        return new JoinResult(
                user.getId(),
                user.getUserName()
        );
    }

    public static LoginResult from(String token) {
        return new LoginResult(token);
    }

}
