package com.task;

import com.task.dto.UserDto;
import com.task.entity.User;
import com.task.exception.handler.ExceptionResponse;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ModelUtils {

    public static User getUser() {
        return User.builder()
                .id(1)
                .email("test@mail.com")
                .firstName("test first name")
                .lastName("test last name")
                .birthDate(LocalDate.of(2000, 10, 10))
                .address("test address")
                .phoneNumber(50003445)
                .build();
    }

    public static UserDto getUserDto() {
        return UserDto.builder()
                .email("test@mail.com")
                .firstName("test first name")
                .lastName("test last name")
                .birthDate(LocalDate.of(2000, 10, 10))
                .address("test address")
                .phoneNumber(50003445)
                .build();
    }

    public static ExceptionResponse getExceptionResponse() {
        ZonedDateTime date = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setMessage("test");
        exceptionResponse.setDateTime(date.format(formatter));
        return exceptionResponse;
    }
}
