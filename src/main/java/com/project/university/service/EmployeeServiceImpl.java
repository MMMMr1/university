package com.project.university.service;

import com.project.university.core.exception.InvalidVersionException;
import com.project.university.core.exception.EntityNotFoundException;
import com.project.university.database.repository.api.EmployeeRepository;
import com.project.university.core.dto.employee.EmployeeCreateEditDto;
import com.project.university.core.dto.employee.EmployeeReadDto;
import com.project.university.core.mapper.employee.EmployeeCreateEditMapper;
import com.project.university.core.mapper.employee.EmployeeReadMapper;
import com.project.university.service.api.EmployeeService;
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
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;
    private final EmployeeCreateEditMapper employeeCreateEditMapper;
    private final EmployeeReadMapper employeeReadMapper;

    @Transactional
    public void create(EmployeeCreateEditDto userDto) {
        Optional.of(userDto)
                .map(employeeCreateEditMapper::map)
                .map(employee -> {
                    employee.setUuid(UUID.randomUUID());
                    employee.setDtCreate(Instant.now());
                    employee.setVersion(Instant.now().toEpochMilli());
                    return employee;
                })
                .map(repository::save)
                .orElseThrow();
    }

    public EmployeeReadDto getUser(UUID uuid) {
        return repository.findById(uuid)
                .map(employeeReadMapper::map)
                .orElseThrow(() -> new EntityNotFoundException("There is no employee " + uuid));
    }

    public Page<EmployeeReadDto> getAll(Pageable paging) {
        return repository.findAll(paging)
                .map(employeeReadMapper::map);
    }

    @Transactional
    public EmployeeReadDto update(UUID uuid, Long version, EmployeeCreateEditDto employee) {
        return repository.findById(uuid)
                .map(entity -> {
                    if (!entity.getVersion().equals(version)) {
                        log.error("Can not update employee " + uuid + "invalid version " + version);
                        throw new InvalidVersionException("Invalid version");
                    }
                    return employeeCreateEditMapper.map(employee, entity);
                })
                .map(repository::saveAndFlush)
                .map(employeeReadMapper::map)
                .orElseThrow(() -> new EntityNotFoundException("There is no employee " + uuid));
    }

    @Transactional
    public boolean delete(UUID uuid) {
        return repository.findById(uuid)
                .map(entity -> {
                    repository.delete(entity);
                    repository.flush();
                    return true;
                })
                .orElse(false);
    }
}
