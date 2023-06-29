package com.project.university.core.dto.student;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.university.core.jackson.CustomInstantConverter;
import lombok.Value;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Value
public class StudentReadDto {
    UUID uuid;
    String name;
    LocalDate birthDate;
    String passport;
    String address;
    String phone;
    @JsonSerialize(converter = CustomInstantConverter.Serializer.class)
    Instant dtCreate;
    Long version;
}

