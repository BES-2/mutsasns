package com.likelionproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.likelionproject.domain.dto.joindto.Result;
import com.likelionproject.domain.dto.joindto.UserJoinRequest;
import com.likelionproject.domain.dto.joindto.UserJoinResponse;
import com.likelionproject.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    @Test
    @DisplayName("Join 기능 (정상/중복) 잘 작동하는지 테스트")
    void userJoin() {
        UserJoinRequest userJoinRequest = new UserJoinRequest("chuu", "jiwoo");
        Result result = new Result(1l, "chuu");
        given(userService.join(userJoinRequest)).willReturn(new UserJoinResponse("SUCCESS", result));
    }

    @Test
    void userLogin() {
    }
}