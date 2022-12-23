package com.likelionproject.controller;

import com.likelionproject.domain.dto.logindto.UserLoginRequest;
import com.likelionproject.domain.dto.logindto.UserLoginResponse;
import com.likelionproject.domain.dto.joindto.UserJoinResponse;
import com.likelionproject.domain.dto.joindto.UserJoinRequest;
import com.likelionproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<UserJoinResponse> userJoin(@RequestBody UserJoinRequest userJoinRequest) {
        return ResponseEntity.ok().body(userService.join(userJoinRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> userLogin(@RequestBody UserLoginRequest userLoginRequest) {
        return ResponseEntity.ok().body(userService.login(userLoginRequest.getUserName(), userLoginRequest.getPassword()));
    }

}
