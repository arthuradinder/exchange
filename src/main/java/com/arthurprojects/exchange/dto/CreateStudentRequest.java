package com.arthurprojects.exchange.dto;

import com.arthurprojects.exchange.entity.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateStudentRequest {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String placeOfBirth;
    private String countryOfOrigin;
}
