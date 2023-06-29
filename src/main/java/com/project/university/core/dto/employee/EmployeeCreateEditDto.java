package com.project.university.core.dto.employee;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Value;

import java.time.LocalDate;

@Value
public class EmployeeCreateEditDto {
    @NotBlank
    String name;
    LocalDate birthDate;
    @NotBlank
    String passport;
    @NotBlank
    String address;
    @NotBlank
    String phone;
}
