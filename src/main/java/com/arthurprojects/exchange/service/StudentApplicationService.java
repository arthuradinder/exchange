package com.arthurprojects.exchange.service;

import com.arthurprojects.exchange.dto.CreateStudentApplicationRequest;
import com.arthurprojects.exchange.entity.StudentApplication;
import com.arthurprojects.exchange.repository.StudentApplicationRepository;
import com.arthurprojects.exchange.util.ApplicationIdGenerator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentApplicationService {
    private final StudentApplicationRepository repository;
    private final ApplicationIdGenerator idGenerator;

    @Transactional
    public StudentApplication createApplication(CreateStudentApplicationRequest request) {
        log.info("Creating new student application");

        // Validate request
        if (request.getHobbies() == null || request.getHobbies().size() < 3) {
            throw new IllegalArgumentException("At least three hobbies are required");
        }

        // Check for existing passport number
        if (repository.existsByPassportNumber(request.getPassportNumber())) {
            throw new IllegalArgumentException("Passport number already exists: " + request.getPassportNumber());
        }

        // Check for existing student application
        if (repository.existsByStudentId(request.getStudentId())) {
            throw new IllegalArgumentException("Student already has an application: " + request.getStudentId());
        }

        try {
            StudentApplication application = new StudentApplication();
            application.setApplicantId(idGenerator.generateApplicationId());
            application.setStudentId(request.getStudentId());
            application.setNumberOfLanguages(request.getNumberOfLanguages());
            application.setLanguagesList(request.getLanguagesList());
            application.setPassportNumber(request.getPassportNumber());
            application.setHobbies(request.getHobbies());
            application.setHomeAcademyId(request.getHomeAcademyId());
            application.setMentorId(request.getMentorId());
            application.setSciencesOption(request.getSciencesOption());
            application.setStudentType(request.getStudentType());
            application.setDormParentId(request.getDormParentId());

            StudentApplication savedApplication = repository.save(application);
            log.info("Created application with ID: {}", savedApplication.getApplicantId());
            return savedApplication;
        } catch (Exception e) {
            log.error("Error creating application: ", e);
            throw new RuntimeException("Failed to create application: " + e.getMessage());
        }
    }

    public StudentApplication getApplication(String applicantId) {
        return repository.findById(applicantId)
                .orElseThrow(() -> new EntityNotFoundException("Application not found: " + applicantId));
    }

    public List<StudentApplication> getAllApplications() {
        return repository.findAll();
    }

    @Transactional
    public void deleteApplication(String applicantId) {
        if (!repository.existsById(applicantId)) {
            throw new EntityNotFoundException("Application not found: " + applicantId);
        }
        repository.deleteById(applicantId);
        log.info("Deleted application with ID: {}", applicantId);
    }
}
