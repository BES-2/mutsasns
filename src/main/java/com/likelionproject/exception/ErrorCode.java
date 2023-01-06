package com.likelionproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    DUPLICATED_USERNAME(HttpStatus.CONFLICT, "UserName이 중복됩니다."),
    INVALID_PASSWORD(HttpStatus.CONFLICT, "패스워드가 잘못되었습니다."),
    POST_NOT_FOUNDED(HttpStatus.CONFLICT, "해당 포스트가 없습니다."),
    USER_NOT_FOUNDED(HttpStatus.CONFLICT, "Not founded"),
    COMMENT_NOT_EXIST(HttpStatus.CONFLICT, "해당 게시글에 댓글이 없습니다."),
    COMMENT_NOT_FOUNDED(HttpStatus.CONFLICT, "댓글을 찾을 수 없습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "잘못된 토큰입니다."),
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "DB에러"),
    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "사용자가 권한이 없습니다.");

    private HttpStatus status;
    private String message;
}
