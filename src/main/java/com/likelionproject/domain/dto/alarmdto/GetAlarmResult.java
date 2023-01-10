package com.likelionproject.domain.dto.alarmdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class GetAlarmResult {
    private Long id;
    private String alarmType;
    private Long fromUserId;
    private Long targetId;
    private String text;
    private LocalDateTime createdAt;
}
