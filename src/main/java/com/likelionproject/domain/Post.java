package com.likelionproject.domain;

import com.likelionproject.domain.dto.postdto.request.PostModifyRequest;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@Table(name ="post")
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Post extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;

    private String title;
    private String body;

    public void modifyPost(PostModifyRequest postModifyRequest) {
        this.title = postModifyRequest.getTitle();
        this.body = postModifyRequest.getBody();
        this.setLastModifiedAt(LocalDateTime.now());
    }

}
