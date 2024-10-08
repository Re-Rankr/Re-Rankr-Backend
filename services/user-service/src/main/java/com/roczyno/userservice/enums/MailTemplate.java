package com.roczyno.userservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MailTemplate {
    VERIFICATION("activate_account","Activate your account"),
    RESET("reset_account","Reset Password");

    private final String name;
    private final String subject;
}
