package com.project.university.web.controllers;

import com.project.university.core.dto.employee.EmployeeCreateEditDto;
import com.project.university.core.dto.employee.EmployeeReadDto;
import com.project.university.service.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeServiceImpl service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody @Validated EmployeeCreateEditDto user)   {
        log.info("create "+ user);
        service.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(path = "/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<EmployeeReadDto> get(@PathVariable("uuid") UUID uuid) {
        log.info("get user with "+ uuid);
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getUser(uuid));
    }
    @RequestMapping(method = RequestMethod.GET)
    protected ResponseEntity<Page<EmployeeReadDto>> getAll(
            @RequestParam(name = "page", defaultValue = "0")  Integer page,
            @RequestParam(name = "size", defaultValue = "20") Integer size) {
        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAll(paging));
    }

    @RequestMapping(path = "/{uuid}/version/{version}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("uuid") UUID uuid,
                                    @PathVariable("version") Long version,
                                    @RequestBody @Validated EmployeeCreateEditDto user) {
        service.update(uuid, version, user);
        log.info("update user with "+ uuid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @RequestMapping(path = "/{uuid} ", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("uuid") UUID uuid ) {
        log.info("delete user with "+ uuid);
        return service.delete(uuid)
                ? ResponseEntity.status(HttpStatus.OK).build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
