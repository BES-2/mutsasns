package com.likelionproject.domain.dto;

import com.likelionproject.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PostDto {
    private Long id;
    private String title;
    private String body;
    private User user;
}
