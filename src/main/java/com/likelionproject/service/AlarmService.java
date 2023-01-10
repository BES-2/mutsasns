package com.likelionproject.service;

import com.likelionproject.domain.dto.Response;
import com.likelionproject.domain.dto.alarmdto.GetAlarmResult;
import com.likelionproject.domain.entity.Alarm;
import com.likelionproject.domain.entity.User;
import com.likelionproject.exception.AppException;
import com.likelionproject.exception.ErrorCode;
import com.likelionproject.repository.AlarmRepository;
import com.likelionproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlarmService {

    private final AlarmRepository alarmRepository;
    private final UserRepository userRepository;

    public Response<List<GetAlarmResult>> getAlarm(String userName, Pageable pageable) {
        User loginUser = userRepository.findByUserName(userName).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUNDED));
        List<GetAlarmResult> alarms = alarmRepository.findByUserId(loginUser.getId(), pageable).stream()
                .map(alarm -> GetAlarmResult.builder()
                        .id(alarm.getId())
                        .alarmType(alarm.getAlarmType())
                        .fromUserId(alarm.getFromUserId())
                        .targetId(alarm.getTargetId())
                        .text(alarm.getText())
                        .createdAt(alarm.getCreatedAt())
                        .build())
                .collect(Collectors.toList());

        return Response.success(alarms);
    }
}
