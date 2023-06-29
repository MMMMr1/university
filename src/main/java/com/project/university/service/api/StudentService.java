package com.project.university.service.api;

import com.project.university.core.dto.student.StudentCreateEditDto;
import com.project.university.core.dto.student.StudentReadDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.UUID;

public interface StudentService {
    void   create(StudentCreateEditDto userDto);
    StudentReadDto  getUser(UUID uuid);
    Page<StudentReadDto> getAll(Pageable paging);
    StudentReadDto update(UUID uuid, Long version, StudentCreateEditDto user);
    boolean delete(UUID uuid);
}
