package com.roczyno.userservice.dto;

import java.util.UUID;

public record ResetPasswordRequest(
        String otp,
        String email,
        String newPassword
) {
}
