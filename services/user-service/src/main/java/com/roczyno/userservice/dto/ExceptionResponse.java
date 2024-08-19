package com.roczyno.userservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Set;

@Data
@Builder
public class ExceptionResponse {
    private Integer status;
    private Set<String> message;
}
