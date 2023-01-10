package com.likelionproject.domain.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "alarms")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Alarm extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String alarmType;
    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private Long fromUserId;
    private Long targetId;
    private String text;

    public Alarm toEntity(String alarmType, User writingUser, Long targetId, String text) {
        return Alarm.builder()
                .alarmType(alarmType)
                .user(writingUser)
                .fromUserId(writingUser.getId())
                .targetId(targetId)
                .text(text)
                .build();
    }
}
