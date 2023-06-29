package com.project.university.core.mapper.educationProcess;

import com.project.university.core.dto.educationProcess.EducationProcessReadDto;
import com.project.university.core.mapper.Mapper;
import com.project.university.database.entity.EducationProcessEntity;
import org.springframework.stereotype.Component;


@Component
public class EducationProcessReadMapper implements Mapper<EducationProcessEntity, EducationProcessReadDto> {
    @Override
    public EducationProcessReadDto map(EducationProcessEntity object) {
        return new EducationProcessReadDto(
                object.getUuid(),
                object.getStudent().getUuid(),
                object.getTutor().getUuid(),
                object.getSubject().getSubject(),
                object.getMark(),
                object.getDtCreate(),
                object.getVersion()
        );
    }
}
