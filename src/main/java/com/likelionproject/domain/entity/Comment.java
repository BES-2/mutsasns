package com.likelionproject.domain.entity;

import com.likelionproject.domain.dto.commentdto.request.CommentModifyRequest;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;

    private String comment;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    public void modifyComment(CommentModifyRequest commentModifyRequest) {
        this.comment = commentModifyRequest.getComment();
        this.setLastModifiedAt(LocalDateTime.now());
    }

}
