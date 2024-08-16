package com.roczyno.userservice.dto;

import lombok.Builder;

@Builder
public record UserDto(
        String id,
        String username,
        String email
) {
}
