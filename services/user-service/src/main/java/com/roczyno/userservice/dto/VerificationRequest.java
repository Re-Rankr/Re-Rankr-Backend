package com.roczyno.userservice.dto;

import com.roczyno.userservice.enums.OtpType;

public record VerificationRequest(
        String email,
        String value,
        OtpType type
) {
}
