package com.arthurprojects.exchange.controller;

import com.arthurprojects.exchange.dto.ApiResponse;
import com.arthurprojects.exchange.dto.CreateStudentApplicationRequest;
import com.arthurprojects.exchange.entity.StudentApplication;
import com.arthurprojects.exchange.service.StudentApplicationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@Slf4j
@RequiredArgsConstructor
public class StudentApplicationController {
    private final StudentApplicationService service;
    @PostMapping
    public ResponseEntity<ApiResponse<StudentApplication>> createApplication(
            @Validated @RequestBody CreateStudentApplicationRequest request) {
        log.info("Received request to create application: {}", request);
        try {
            StudentApplication application = service.createApplication(request);
            return new ResponseEntity<>(
                    ApiResponse.success("Application created successfully", application),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            log.error("Error creating application: ", e);
            return new ResponseEntity<>(
                    ApiResponse.error("Cannot create application: " + e.getMessage()),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/{applicantId}")
    public ResponseEntity<ApiResponse<StudentApplication>> getApplication(@PathVariable String applicantId) {
        log.info("Fetching application with ID: {}", applicantId);
        try {
            StudentApplication application = service.getApplication(applicantId);
            return ResponseEntity.ok(
                    ApiResponse.success("Application retrieved successfully", application)
            );
        } catch (Exception e) {
            log.error("Error retrieving application: ", e);
            return new ResponseEntity<>(
                    ApiResponse.error("Cannot retrieve application: " + e.getMessage()),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentApplication>>> getAllApplications() {
        log.info("Fetching all applications");
        try {
            List<StudentApplication> applications = service.getAllApplications();
            return ResponseEntity.ok(
                    ApiResponse.success("Applications retrieved successfully", applications)
            );
        } catch (Exception e) {
            log.error("Error retrieving applications: ", e);
            return new ResponseEntity<>(
                    ApiResponse.error("Cannot retrieve applications: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @DeleteMapping("/{applicantId}")
    public ResponseEntity<ApiResponse<Void>> deleteApplication(@PathVariable String applicantId) {
        log.info("Deleting application with ID: {}", applicantId);
        try {
            service.deleteApplication(applicantId);
            return ResponseEntity.ok(
                    ApiResponse.success("Application deleted successfully", null)
            );
        } catch (Exception e) {
            log.error("Error deleting application: ", e);
            return new ResponseEntity<>(
                    ApiResponse.error("Cannot delete application: " + e.getMessage()),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/test")
    public ResponseEntity<ApiResponse<String>> test() {
        return ResponseEntity.ok(
                ApiResponse.success("StudentApplicationController is working!", "Test successful")
        );
    }
}
