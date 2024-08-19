package com.roczyno.userservice.constants;

public final class Patterns {
    public static final String EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final String PASSWORD = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\s:])(\\S){8,}$";

    private Patterns() {
        throw new IllegalStateException(ErrorConstants.UTILITY_CLASS);
    }
}
