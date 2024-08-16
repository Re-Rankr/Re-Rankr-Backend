package com.roczyno.userservice.dto;

public record AuthRequest(
        String email,
        String username,
        String password
) {
}
