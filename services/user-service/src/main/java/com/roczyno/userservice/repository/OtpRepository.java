package com.roczyno.userservice.repository;

import com.roczyno.userservice.entity.OTP;
import com.roczyno.userservice.entity.User;
import com.roczyno.userservice.enums.OtpType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OtpRepository extends JpaRepository<OTP,Long> {
    List<OTP> findByExpiresAtBefore(LocalDateTime presentTime);
    Optional<OTP> findByValueAndUser(String value, User user);
    Optional<OTP> findByUserAndType(User user, OtpType type);
}
