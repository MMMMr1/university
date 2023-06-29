package com.project.university.core.mapper.employee;

import com.project.university.core.mapper.Mapper;
import com.project.university.database.entity.EmployeeEntity;
import com.project.university.core.dto.employee.EmployeeCreateEditDto;
import org.springframework.stereotype.Component;

@Component
public class EmployeeCreateEditMapper implements Mapper<EmployeeCreateEditDto, EmployeeEntity> {
    @Override
    public EmployeeEntity map(EmployeeCreateEditDto fromObject, EmployeeEntity toObject) {
        toObject.setName(fromObject.getName());
        toObject.setPassport(fromObject.getPassport());
        toObject.setBirthDate(fromObject.getBirthDate());
        toObject.setPhone(fromObject.getPhone());
        toObject.setAddress(fromObject.getAddress());
        return toObject;
    }
    @Override
    public EmployeeEntity map(EmployeeCreateEditDto object) {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setName(object.getName());
        employee.setPassport(object.getPassport());
        employee.setBirthDate(object.getBirthDate());
        employee.setPhone(object.getPhone());
        employee.setAddress(object.getAddress());
        return employee;
    }
}