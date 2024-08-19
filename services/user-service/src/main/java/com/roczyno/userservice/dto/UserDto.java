package com.roczyno.userservice.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserDto(
        UUID id,
        String username,
        String email
) {
}
