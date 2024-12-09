package com.arthurprojects.exchange.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="student")
@Data
@NoArgsConstructor
public class Student {
    @Id
    @Column(name ="student_id", length = 20)
    private String studentId;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(nullable = false, name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(name = "place_of_birth", nullable = false)
    private String placeOfBirth;

    @Column(nullable = false, name = "country_of_origin")
    private String countryOfOrigin;

    public Student(String countryOfOrigin, String placeOfBirth,
                   String lastName, Gender gender,
                   String firstName, LocalDate dateOfBirth) {
        this.countryOfOrigin = countryOfOrigin;
        this.placeOfBirth = placeOfBirth;
        this.lastName = lastName;
        this.gender = gender;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
    }
}
