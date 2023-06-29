package com.project.university.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Data
@ToString(exclude = "educationProcesses")
@EqualsAndHashCode(of = "name")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tutors")
@Entity
public class EmployeeEntity implements Serializable   {
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
    @OneToMany(mappedBy = "tutor", cascade = CascadeType.PERSIST)
    private Set<EducationProcessEntity> educationProcesses = new HashSet<>();
}
