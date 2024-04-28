package com.task.exception.handler;

import lombok.Data;

@Data
public class ExceptionResponse {
    private Integer errorCode;
    private String message;
    private String dateTime;
}
