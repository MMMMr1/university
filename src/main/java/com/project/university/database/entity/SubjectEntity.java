package com.project.university.database.entity;

import com.project.university.core.enums.Subject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "subjects")
@Entity
public class SubjectEntity {
    @Id
    @Enumerated(EnumType.STRING)
    private Subject subject;
}
