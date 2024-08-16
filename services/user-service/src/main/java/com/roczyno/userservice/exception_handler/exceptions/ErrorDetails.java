package com.roczyno.userservice.exception_handler.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorDetails {
    private String error;
    private String details;
    private LocalDateTime timeStamp;

    public ErrorDetails(String error, String details, LocalDateTime timeStamp) {
        super();
        this.error = error;
        this.details = details;
        this.timeStamp = timeStamp;
    }
}
