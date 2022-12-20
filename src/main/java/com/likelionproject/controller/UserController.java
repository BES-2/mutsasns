package com.likelionproject.controller;

import com.likelionproject.dto.UserJoinResponse;
import com.likelionproject.dto.joindto.UserJoinRequest;
import com.likelionproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    @ResponseBody
    public ResponseEntity<UserJoinResponse> userJoin(@RequestBody UserJoinRequest userJoinRequest) {
        UserJoinResponse userJoinResponse = userService.join(userJoinRequest);
        return ResponseEntity.ok().body(userJoinResponse);
    }

}
