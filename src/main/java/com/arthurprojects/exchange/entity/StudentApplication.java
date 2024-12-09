package com.arthurprojects.exchange.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "studentApplication")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentApplication {
    @Id
    @Column(name = "applicantId", nullable = false)
    private String applicantId;  // We'll auto-generate this like "APP001"

    @Column(name = "studentId", nullable = false)
    private String studentId;    // Reference to Student entity

    @Column(name = "number_of_languages", nullable = false)
    private Integer numberOfLanguages;

    @ElementCollection
    @CollectionTable(
            name = "studentLanguages",
            joinColumns = @JoinColumn(name = "applicantId")
    )
    @Column(name = "language")
    private List<String> languagesList;

    @Column(name = "passport_number", nullable = false, unique = true)
    private String passportNumber;

    @ElementCollection
    @CollectionTable(
            name = "studentHobbies",
            joinColumns = @JoinColumn(name = "applicantId")
    )
    @Column(name = "hobby")
    private List<String> hobbies;

    @Column(name = "homeAcademyId")
    private String homeAcademyId;  // Will be foreign key to Academy entity

    @Column(name = "mentorId")
    private String mentorId;       // Will be foreign key to Mentor entity

    @Column(name = "sciencesOption")
    private String sciencesOption; // Will be fetched from Academy entity

    @Enumerated(EnumType.STRING)
    @Column(name = "studentType", nullable = false)
    private StudentType studentType;

    @Column(name = "dormParentId")
    private String dormParentId;   // Will be foreign key to DormParent entity

    // Validation for minimum 3 hobbies
    @PrePersist
    @PreUpdate
    private void validateHobbies() {
        if (hobbies == null || hobbies.size() < 3) {
            throw new IllegalStateException("Student must have at least 3 hobbies");
        }
    }
}
