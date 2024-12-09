package com.arthurprojects.exchange.dto;

import com.arthurprojects.exchange.entity.StudentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CreateStudentApplicationRequest {
    @NotBlank(message = "Student ID is required")
    private String studentId;

    @NotNull(message = "Number of languages is required")
    private Integer numberOfLanguages;

    @NotNull(message = "Languages list is required")
    @Size(min = 1, message = "At least one language must be specified")
    private List<String> languagesList;

    @NotBlank(message = "Passport number is required")
    private String passportNumber;

    @NotNull(message = "Hobbies list is required")
    @Size(min = 3, message = "Student must have at least three hobbies")  // Changed this message
    private List<String> hobbies;

    @NotBlank(message = "Home academy ID is required")
    private String homeAcademyId;

    @NotBlank(message = "Mentor ID is required")
    private String mentorId;

    @NotBlank(message = "Sciences option is required")
    private String sciencesOption;

    @NotNull(message = "Student type is required")
    private StudentType studentType;

    private String dormParentId;
}
