package com.project.university.core.dto.educationProcess;

 import com.project.university.core.enums.Subject;
 import jakarta.validation.constraints.Max;
 import jakarta.validation.constraints.Min;
 import jakarta.validation.constraints.NotNull;
 import lombok.Value;

 import java.util.UUID;

@Value
public class EducationProcessCreateEditDto {
    @NotNull
    UUID student;
    @NotNull
    UUID tutor;
    Subject subject;
    @Min(value = 0, message = "Mark should not be less than 0")
    @Max(value = 100, message = "Mark should not be greater than 100")
    Integer mark;
}
