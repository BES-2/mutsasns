package com.likelionproject.service;

import com.likelionproject.dto.UserJoinResponse;
import com.likelionproject.dto.joindto.UserJoinRequest;
import com.likelionproject.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import com.likelionproject.dto.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User toEntity(UserJoinRequest userJoinRequest) {
        return User.builder()
                .userName(userJoinRequest.getUserName())
                .password(userJoinRequest.getPassword())
                .build();
    }

    public UserJoinResponse join(UserJoinRequest userJoinRequest) {
        User newUser = userRepository.save(toEntity(userJoinRequest));
        return new UserJoinResponse(newUser.getUserName(), newUser.getPassword());
    }
}
