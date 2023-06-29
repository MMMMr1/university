package com.project.university.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "educationProcess")
@Entity
public class EducationProcessEntity {
    @Id
    private UUID uuid;
    @ManyToOne()
    @JoinColumn(name = "student_id")
    private StudentEntity student;
    @ManyToOne()
    @JoinColumn(name = "tutor_id")
    private EmployeeEntity tutor;
    @ManyToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "subject")
    private SubjectEntity subject;
    private int mark;
    @Column(name = "dtcreate")
    private Instant dtCreate;
    @Version
    private Long version;
    public EmployeeEntity getTutor() {
        return tutor;
    }
    public void setTutor(EmployeeEntity tutor) {
        this.tutor = tutor;
    }

}
