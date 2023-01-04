package com.likelionproject.domain.dto.commentdto.result;

import com.likelionproject.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Getter
public class CommentResultFactory {

    public static CommentCreateResult from(Comment comment) {
        return CommentCreateResult.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .userName(comment.getUser().getUserName())
                .postId(comment.getPost().getId())
                .createdAt(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(comment.getCreatedAt()))
                .build();
    }

    public static CommentModifyResult newModifyComment(Comment comment) {
        return CommentModifyResult.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .userName(comment.getUser().getUserName())
                .postId(comment.getPost().getId())
                .createdAt(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(comment.getCreatedAt()))
                .lastModifiedAt(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(comment.getLastModifiedAt()))
                .build();
    }

}
