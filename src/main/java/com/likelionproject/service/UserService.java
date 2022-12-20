package com.likelionproject.service;

import com.likelionproject.dto.UserDto;
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

    public UserDto join(UserJoinRequest userJoinRequest) {
        User newUser = userRepository.save(userJoinRequest.toEntity());

        return UserDto.builder()
                .id(newUser.getId())
                .userName(newUser.getUserName())
                .build();
    }
}
