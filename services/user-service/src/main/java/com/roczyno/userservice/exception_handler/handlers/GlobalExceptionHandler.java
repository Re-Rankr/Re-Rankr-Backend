package com.roczyno.userservice.exception_handler.handlers;

import com.roczyno.userservice.exception_handler.exceptions.ErrorDetails;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest req) {
        logException(ex);
        List<ErrorDetails> errors = Collections.singletonList(
                new ErrorDetails(ex.getMessage(), req.getDescription(false), LocalDateTime.now())
        );
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void logException(Exception ex) {
        logger.error("Exception: ", ex);
    }
}
