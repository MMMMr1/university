package com.project.university.web.controllers;

import com.project.university.core.dto.educationProcess.EducationProcessCreateEditDto;
import com.project.university.core.dto.educationProcess.EducationProcessReadDto;
import com.project.university.service.EducationProcessServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/education_processes")
@RequiredArgsConstructor
public class EducationProcessController {
    private final EducationProcessServiceImpl service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody @Validated EducationProcessCreateEditDto educationProcess)   {
        log.info("create "+ educationProcess);
        service.create(educationProcess);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(path = "/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<EducationProcessReadDto> get(@PathVariable("uuid") UUID uuid) {
        log.info("get education process card with "+ uuid);
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getEducationProcess(uuid));
    }
    @RequestMapping(method = RequestMethod.GET)
    protected ResponseEntity<Page<EducationProcessReadDto>> getAll(
            @RequestParam(name = "page", defaultValue = "0")  Integer page,
            @RequestParam(name = "size", defaultValue = "20") Integer size) {
        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAll(paging));
    }

    @RequestMapping(path = "/{uuid}/version/{version}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("uuid") UUID uuid,
                                    @PathVariable("version") Long version,
                                    @RequestBody @Validated EducationProcessCreateEditDto user) {
        service.update(uuid, version, user);
        log.info("update education process card with "+ uuid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @RequestMapping(path = "/{uuid}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") UUID uuid ) {
        log.info("delete education process card with "+ uuid);
        return service.delete(uuid)
                ? ResponseEntity.status(HttpStatus.OK).build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
