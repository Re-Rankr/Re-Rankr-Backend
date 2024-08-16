package com.roczyno.userservice.dto;

import lombok.Builder;

@Builder
public record AuthRequest(
        String email,
        String username,
        String password
) {
}
