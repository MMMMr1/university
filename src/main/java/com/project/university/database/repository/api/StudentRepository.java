package com.project.university.database.repository.api;

import com.project.university.database.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, UUID> ,CrudRepository<StudentEntity, UUID> {

}
