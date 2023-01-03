package com.likelionproject.controller;

import com.likelionproject.domain.dto.Response;
import com.likelionproject.domain.dto.userdto.JoinResult;
import com.likelionproject.domain.dto.userdto.UserLoginRequest;
import com.likelionproject.domain.dto.userdto.UserJoinRequest;
import com.likelionproject.domain.dto.userdto.LoginResult;
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
    public ResponseEntity<Response<JoinResult>> userJoin(@RequestBody UserJoinRequest userJoinRequest) {
        return ResponseEntity.ok().body(userService.join(userJoinRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<Response<LoginResult>> userLogin(@RequestBody UserLoginRequest userLoginRequest) {
        return ResponseEntity.ok().body(userService.login(userLoginRequest.getUserName(), userLoginRequest.getPassword()));
    }

}
