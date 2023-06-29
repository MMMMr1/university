package com.project.university.service.api;

import com.project.university.core.dto.employee.EmployeeCreateEditDto;
import com.project.university.core.dto.employee.EmployeeReadDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.UUID;

public interface EmployeeService {
    void   create(EmployeeCreateEditDto userDto);
    EmployeeReadDto getUser(UUID uuid);
    Page<EmployeeReadDto> getAll(Pageable paging);
    EmployeeReadDto update(UUID uuid, Long version, EmployeeCreateEditDto user);
    boolean delete(UUID uuid);
}
