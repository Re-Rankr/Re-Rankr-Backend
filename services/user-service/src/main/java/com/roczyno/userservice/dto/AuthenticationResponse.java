package com.roczyno.userservice.dto;

import lombok.Builder;

@Builder
public record AuthenticationResponse(
        int status,
        String access_token
) {
}
