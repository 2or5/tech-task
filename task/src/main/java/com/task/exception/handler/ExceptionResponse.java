package com.task.exception.handler;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionResponse {
    private Integer errorCode;
    private String message;
    private Date date;
}
