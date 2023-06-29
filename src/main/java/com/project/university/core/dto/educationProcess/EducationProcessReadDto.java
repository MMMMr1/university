package com.project.university.core.dto.educationProcess;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.university.core.enums.Subject;
import com.project.university.core.jackson.CustomInstantConverter;
import lombok.Value;

import java.time.Instant;
import java.util.UUID;

@Value
public class EducationProcessReadDto {
    UUID uuid;
    UUID student;
    UUID tutor;
    Subject subject;
    Integer mark;
    @JsonSerialize(converter = CustomInstantConverter.Serializer.class)
    Instant dtCreate;
    Long version;
}
