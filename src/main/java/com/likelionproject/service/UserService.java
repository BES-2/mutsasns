package com.likelionproject.service;

import com.likelionproject.domain.dto.UserDto;
import com.likelionproject.domain.dto.joindto.Result;
import com.likelionproject.domain.dto.joindto.UserJoinResponse;
import com.likelionproject.domain.dto.joindto.UserJoinRequest;
import com.likelionproject.exception.ErrorCode;
import com.likelionproject.exception.UserException;
import com.likelionproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.likelionproject.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    public UserJoinResponse join(UserJoinRequest userJoinRequest) {

        if (userRepository.findByUserName(userJoinRequest.getUserName()).isPresent()) {
            throw new UserException(ErrorCode.DUPLICATED_USERNAME);
        }

        User newUser = userRepository.save(userJoinRequest.toEntity(encoder.encode(userJoinRequest.getPassword())));
        UserDto userDto = UserDto.builder()
                .id(newUser.getId())
                .userName(newUser.getUserName())
                .build();
        Result result = new Result(userDto.getId(), userDto.getUserName());
        return new UserJoinResponse("SUCCESS", result);
    }
}
