package com.task.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Builder
public class BirthDateRangeDto {
    private LocalDate birthDateFrom;
    private LocalDate birthDateTo;
}
