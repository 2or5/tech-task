package com.task.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
