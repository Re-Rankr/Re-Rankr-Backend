package com.roczyno.userservice.service.OtpService;

import com.roczyno.userservice.entity.OTP;
import com.roczyno.userservice.entity.User;
import com.roczyno.userservice.enums.OtpType;

import java.util.UUID;


public interface OtpService {
    OTP generateOTP(OtpType type, User user, boolean save);
    User verifyOTP(String value, String email);
    User verifyOTP(String value, UUID id);
    OTP getOTP (UUID id);
}
