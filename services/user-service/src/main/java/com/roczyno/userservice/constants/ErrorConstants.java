package com.roczyno.userservice.constants;

import lombok.Data;

public final class ErrorConstants {
    public static final String UTILITY_CLASS = "This class can't be instantiated";
    public static final String EMAIL_FORMAT = "Invalid email";
    public static final String PASSWORD_FORMAT = "Invalid Password";

    private ErrorConstants() {
        throw new IllegalStateException(UTILITY_CLASS);
    }
}
