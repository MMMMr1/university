package com.project.university.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@ToString(exclude = "examProcesses")
@EqualsAndHashCode(of = "name")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "students")
@Entity
public class StudentEntity implements Serializable {
    @Id
    @Column(name = "id")
    private UUID uuid;
    private String name;
    private LocalDate birthDate;
    private String passport;
    private String address;
    private String phone;
    @Column(name = "dtcreate")
    private Instant dtCreate;
    @Version
    private Long version;
//    @ManyToOne( )
//    @JoinColumn(name = "department_id")
//    private Department department;
//    @ManyToOne(cascade = CascadeType.ALL )
//    @JoinTable(
//            name = "group_student",
//            joinColumns =
//            @JoinColumn(name = "student_id"),
//            inverseJoinColumns =
//            @JoinColumn(name  = "group_id")
//    )
//    private Group group;
    @OneToMany(mappedBy = "student")
    private List<EducationProcessEntity> educationProcesses  = new ArrayList<>();
}