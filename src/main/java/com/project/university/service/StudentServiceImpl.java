package com.project.university.service;

import com.project.university.core.exception.InvalidVersionException;
import com.project.university.core.exception.EntityNotFoundException;
import com.project.university.database.repository.api.StudentRepository;
import com.project.university.core.dto.student.StudentCreateEditDto;
import com.project.university.core.dto.student.StudentReadDto;
import com.project.university.core.mapper.student.StudentReadMapper;
import com.project.university.core.mapper.student.StudentCreateEditMapper;
import com.project.university.service.api.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {
    private final StudentRepository repository;
    private final StudentCreateEditMapper userCreateEditMapper;
    private final StudentReadMapper studentReadMapper;

    @Transactional
    public void create(StudentCreateEditDto userDto) {
        Optional.of(userDto)
                .map(userCreateEditMapper::map)
                .map(student -> {
                    student.setUuid(UUID.randomUUID());
                    student.setDtCreate(Instant.now());
                    student.setVersion(Instant.now().toEpochMilli());
                    return student;
                })
                .map(repository::save)
                .orElseThrow();
    }

    public StudentReadDto getUser(UUID uuid) {
        return repository.findById(uuid)
                .map(studentReadMapper::map)
                .orElseThrow(() -> new EntityNotFoundException("There is no student with "+ uuid));
    }

    public Page<StudentReadDto> getAll(Pageable paging) {
        return repository.findAll(paging)
                .map(studentReadMapper::map);
    }

    @Transactional
    public StudentReadDto update(UUID uuid, Long version, StudentCreateEditDto student) {
        return repository.findById(uuid)
                .map(entity -> {
                    if (!entity.getVersion().equals(version)) {
                        log.error("Can not update student " + uuid + "invalid version " + version);
                        throw new InvalidVersionException("Invalid version");
                    }
                    return userCreateEditMapper.map(student, entity);
                })
                .map(repository::saveAndFlush)
                .map(studentReadMapper::map)
                .orElseThrow(() -> new EntityNotFoundException("There is no student with "+ uuid));
    }

    @Transactional
    public boolean delete(UUID uuid) {
        return repository.findById(uuid)
                .map(entity -> {
                    repository.delete(entity);
                    repository.flush();
                    log.info("delete  " + uuid);
                    return true;
                })
                .orElse(false);
    }
}
