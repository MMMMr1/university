package com.project.university.service;

import com.project.university.core.dto.educationProcess.EducationProcessCreateEditDto;
import com.project.university.core.dto.educationProcess.EducationProcessReadDto;
import com.project.university.core.exception.InvalidVersionException;
import com.project.university.core.exception.EntityNotFoundException;
import com.project.university.core.mapper.educationProcess.EducationProcessCreateEditMapper;
import com.project.university.core.mapper.educationProcess.EducationProcessReadMapper;
import com.project.university.database.repository.api.EducationProcessRepository;
import com.project.university.service.api.EducationProcessService;
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
public class EducationProcessServiceImpl implements EducationProcessService {
    private final EducationProcessRepository repository;
    private final EducationProcessCreateEditMapper processCreateEditMapper;
    private final EducationProcessReadMapper processReadMapper;

    @Transactional
    public void create(EducationProcessCreateEditDto cardDto) {
        Optional.of(cardDto)
                .map(processCreateEditMapper::map)
                .map(card -> {
                    card.setUuid(UUID.randomUUID());
                    card.setDtCreate(Instant.now());
                    card.setVersion(Instant.now().toEpochMilli());
                    return card;
                })
                .map(repository::save)
                .orElseThrow();
    }

    public EducationProcessReadDto getEducationProcess(UUID uuid) {
        return repository.findById(uuid)
                .map(processReadMapper::map)
                .orElseThrow(() -> new EntityNotFoundException("There is no education process card with such id"));
    }

    public Page<EducationProcessReadDto> getAll(Pageable paging) {
        return repository.findAll(paging)
                .map(processReadMapper::map);
    }

    @Transactional
    public EducationProcessReadDto update(UUID uuid, Long version, EducationProcessCreateEditDto cardDto) {
        return repository.findById(uuid)
                .map(entity -> {
                    if (!entity.getVersion().equals(version)) {
                        log.error("Can not update education process card " + uuid + "invalid version " + version);
                        throw new InvalidVersionException("Invalid version");
                    }
                    return processCreateEditMapper.map(cardDto, entity);
                })
                .map(repository::saveAndFlush)
                .map(processReadMapper::map)
                .orElseThrow(() -> new EntityNotFoundException("There is no education process card with such id"));
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
