package com.project.university.database.repository.api;

import com.project.university.database.entity.EducationProcessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EducationProcessRepository extends JpaRepository<EducationProcessEntity, UUID> ,CrudRepository<EducationProcessEntity, UUID> {

}
