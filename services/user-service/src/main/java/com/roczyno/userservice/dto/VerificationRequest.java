package com.roczyno.userservice.dto;

public record VerificationRequest(
        String email,
        String value
) {
}
