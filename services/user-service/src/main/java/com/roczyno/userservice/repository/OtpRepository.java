package com.roczyno.userservice.repository;

import com.roczyno.userservice.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<OTP,Long> {
}
