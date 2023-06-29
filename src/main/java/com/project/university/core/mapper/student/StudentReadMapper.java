package com.project.university.core.mapper.student;

import com.project.university.core.mapper.Mapper;
import com.project.university.database.entity.StudentEntity;
import com.project.university.core.dto.student.StudentReadDto;
import org.springframework.stereotype.Component;

@Component
public class StudentReadMapper implements Mapper<StudentEntity, StudentReadDto> {

    @Override
    public StudentReadDto map(StudentEntity object) {
        return new StudentReadDto(
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
