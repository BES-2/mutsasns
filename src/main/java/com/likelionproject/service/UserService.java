package com.likelionproject.service;

import com.likelionproject.domain.dto.UserDto;
import com.likelionproject.domain.dto.logindto.UserLoginResponse;
import com.likelionproject.domain.dto.result.JoinResult;
import com.likelionproject.domain.dto.joindto.UserJoinResponse;
import com.likelionproject.domain.dto.joindto.UserJoinRequest;
import com.likelionproject.exception.ErrorCode;
import com.likelionproject.exception.AppException;
import com.likelionproject.repository.UserRepository;
import com.likelionproject.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import com.likelionproject.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    @Value("${jwt.secret.token}")
    private String secretKey;

    private Long expiredMs = 1000 * 60 * 60l;

    public UserJoinResponse join(UserJoinRequest userJoinRequest) {

        if (userRepository.findByUserName(userJoinRequest.getUserName()).isPresent()) {
            throw new AppException(ErrorCode.DUPLICATED_USERNAME);
        }

        User newUser = userRepository.save(userJoinRequest.toEntity(encoder.encode(userJoinRequest.getPassword())));
        UserDto userDto = UserDto.builder()
                .id(newUser.getId())
                .userName(newUser.getUserName())
                .build();
        JoinResult result = new JoinResult(userDto.getId(), userDto.getUserName());
        return new UserJoinResponse("SUCCESS", result);
    }

    public UserLoginResponse login(String userName, String password) {
        User loginUser = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUNDED));

        if(!encoder.matches(password, loginUser.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD);
        }

        UserLoginResponse userLoginResponse = new UserLoginResponse(JwtUtil.createJwt(userName, secretKey, expiredMs));
        return userLoginResponse;
    }
}
