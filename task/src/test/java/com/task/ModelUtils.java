package com.task;

import com.task.dto.UserDto;
import com.task.entity.User;

import java.time.LocalDate;

public class ModelUtils {

    public static User getUser() {
        return   User.builder()
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
}