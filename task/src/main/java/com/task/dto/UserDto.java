package com.task.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotEmpty(message = "email can't be empty")
    @Email(message = "You must enter email")
    private String email;
    @NotBlank(message = "first name can't be empty")
    private String firstName;
    @NotBlank(message = "last name can't be empty")
    private String lastName;
    @Past(message = "start shouldn't be before current date")
    @NotNull(message = "birth date can't be empty")
    private LocalDate birthDate;
    private String address;
    private String phoneNumber;
}
