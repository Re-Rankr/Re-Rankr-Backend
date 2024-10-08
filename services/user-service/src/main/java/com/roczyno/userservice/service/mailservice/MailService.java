package com.roczyno.userservice.service.mailservice;

import com.roczyno.userservice.entity.OTP;

public interface MailService {
    void sendVerificationMail(OTP otp);
    public void sendResetMail(OTP otp);
}
