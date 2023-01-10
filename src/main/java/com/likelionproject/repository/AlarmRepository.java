package com.likelionproject.repository;

import com.likelionproject.domain.entity.Alarm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
    Page<Alarm> findByUserId(Long userId, Pageable pageable);
}
