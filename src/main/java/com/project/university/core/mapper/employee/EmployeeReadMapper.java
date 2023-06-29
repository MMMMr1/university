package com.project.university.core.mapper.employee;

import com.project.university.core.mapper.Mapper;
import com.project.university.database.entity.EmployeeEntity;
import com.project.university.core.dto.employee.EmployeeReadDto;
import org.springframework.stereotype.Component;

@Component
public class EmployeeReadMapper implements Mapper<EmployeeEntity, EmployeeReadDto> {
    @Override
    public EmployeeReadDto map(EmployeeEntity object) {
        return new EmployeeReadDto(
                object.getUuid(),
                object.getName(),
                object.getBirthDate(),
                object.getAddress(),
                object.getPhone(),
                object.getPassport(),
                object.getDtCreate(),
                object.getVersion()
        );
    }
}
