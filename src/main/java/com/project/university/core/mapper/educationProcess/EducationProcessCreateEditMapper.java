package com.project.university.core.mapper.educationProcess;

import com.project.university.core.dto.educationProcess.EducationProcessCreateEditDto;
import com.project.university.core.mapper.Mapper;
import com.project.university.database.entity.EducationProcessEntity;
import com.project.university.database.entity.EmployeeEntity;
import com.project.university.database.entity.StudentEntity;
import com.project.university.database.entity.SubjectEntity;
import com.project.university.database.repository.api.EmployeeRepository;
import com.project.university.database.repository.api.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EducationProcessCreateEditMapper implements Mapper<EducationProcessCreateEditDto, EducationProcessEntity> {
    private final StudentRepository studentRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public EducationProcessEntity map(EducationProcessCreateEditDto object) {
        EducationProcessEntity educationProcess = new EducationProcessEntity();
        educationProcess.setTutor(getEmployee(object.getTutor()));
        educationProcess.setStudent(getStudent(object.getStudent()));
        educationProcess.setMark(object.getMark());
        educationProcess.setSubject(new SubjectEntity(object.getSubject()));
        return educationProcess;
    }

    @Override
    public EducationProcessEntity map(EducationProcessCreateEditDto fromObject, EducationProcessEntity toObject) {
        toObject.setTutor(getEmployee(fromObject.getTutor()));
        toObject.setStudent(getStudent(fromObject.getStudent()));
        toObject.setMark(fromObject.getMark());
        toObject.setSubject(new SubjectEntity(fromObject.getSubject()));
        return Mapper.super.map(fromObject, toObject);
    }
    private EmployeeEntity getEmployee(UUID uuid){
        return Optional.ofNullable(uuid)
                .flatMap(employeeRepository::findById)
                .orElse(null);
    }
    private StudentEntity getStudent(UUID uuid){
        return Optional.ofNullable(uuid)
                .flatMap(studentRepository::findById)
                .orElse(null);
    }

}