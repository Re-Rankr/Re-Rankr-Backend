package com.roczyno.userservice.dto;

import com.roczyno.userservice.constants.ErrorConstants;
import com.roczyno.userservice.constants.Patterns;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;


@Builder
public record AuthRequest(
        @Pattern(regexp = Patterns.EMAIL,message = ErrorConstants.EMAIL_FORMAT)
        String email,
        String username,
        @Pattern(regexp = Patterns.PASSWORD,message = ErrorConstants.PASSWORD_FORMAT)
        String password
) {
}
