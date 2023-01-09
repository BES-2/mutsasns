package com.likelionproject.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Getter
@Entity
@Table(name = "likes")
@AllArgsConstructor
@NoArgsConstructor
public class Like extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    private LocalDateTime deletedAt;

    public Like(User user, Post post) {
        this.user = user;
        this.post = post;
    }

    public void addDeletedAtLike() {
        this.deletedAt = LocalDateTime.now();
    }

    public void setDeleteAtNullLike() {
        this.deletedAt = null;
    }

}
