package com.project.university.core.mapper.student;

import com.project.university.core.mapper.Mapper;
import com.project.university.database.entity.StudentEntity;
import com.project.university.core.dto.student.StudentCreateEditDto;
import org.springframework.stereotype.Component;


@Component
public class StudentCreateEditMapper implements Mapper<StudentCreateEditDto, StudentEntity> {
    @Override
    public StudentEntity map(StudentCreateEditDto fromObject, StudentEntity toObject) {
        toObject.setName(fromObject.getName());
        toObject.setPassport(fromObject.getPassport());
        toObject.setBirthDate(fromObject.getBirthDate());
        toObject.setPhone(fromObject.getPhone());
        toObject.setAddress(fromObject.getAddress());
        return toObject;
    }

    @Override
    public StudentEntity map(StudentCreateEditDto object) {
        StudentEntity student = new StudentEntity();
        student.setName(object.getName());
        student.setPassport(object.getPassport());
        student.setBirthDate(object.getBirthDate());
        student.setPhone(object.getPhone());
        student.setAddress(object.getAddress());
        return student;
    }
}
