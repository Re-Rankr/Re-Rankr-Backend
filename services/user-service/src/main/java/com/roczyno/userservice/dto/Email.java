package com.roczyno.userservice.dto;

import com.roczyno.userservice.enums.MailTemplate;
import lombok.Builder;

@Builder
public record Email(
        String recipient,
        MailTemplate template,
        String subject,
        String name,
        Object body
) {
}
