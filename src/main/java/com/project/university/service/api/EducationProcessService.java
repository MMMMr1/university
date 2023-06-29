package com.project.university.service.api;

import com.project.university.core.dto.educationProcess.EducationProcessReadDto;
import com.project.university.core.dto.employee.EmployeeReadDto;
import com.project.university.core.dto.educationProcess.EducationProcessCreateEditDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.UUID;

public interface EducationProcessService {
    void   create(EducationProcessCreateEditDto userDto);
    EducationProcessReadDto getEducationProcess(UUID uuid);
    Page<EducationProcessReadDto> getAll(Pageable paging);
    EducationProcessReadDto update(UUID uuid, Long version, EducationProcessCreateEditDto user);
    boolean delete(UUID uuid);
}
