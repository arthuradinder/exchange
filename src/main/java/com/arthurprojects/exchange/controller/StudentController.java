package com.arthurprojects.exchange.controller;

import com.arthurprojects.exchange.dto.ApiResponse;
import com.arthurprojects.exchange.dto.CreateStudentRequest;
import com.arthurprojects.exchange.entity.Student;
import com.arthurprojects.exchange.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> createStudent(@RequestBody CreateStudentRequest request) {
        log.info("Received request to create new student");
        try {
            Student createdStudent = studentService.createStudent(request);
            return new ResponseEntity<>(
                    ApiResponse.success("Student created successfully with ID: " + createdStudent.getStudentId(),
                            createdStudent),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            log.error("Error creating student: ", e);
            return new ResponseEntity<>(
                    ApiResponse.error("Cannot create student: " + e.getMessage()),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getAllStudents() {
        log.info("Fetching all students");
        try {
            List<Student> students = studentService.getAllStudents();
            log.info("Retrieved {} students successfully", students.size());
            return ResponseEntity.ok(ApiResponse.success(
                    "Students retrieved successfully",
                    students
            ));
        } catch (Exception e) {
            log.error("Error while fetching all students: ", e);
            return new ResponseEntity<>(
                    ApiResponse.error("Error fetching students: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(@PathVariable String studentId) {
        log.info("Fetching student with ID: {}", studentId);
        try {
            Student student = studentService.getStudentById(studentId);
            log.info("Successfully retrieved student with ID: {}", studentId);
            return ResponseEntity.ok(ApiResponse.success(
                    "Student retrieved successfully",
                    student
            ));
        } catch (EntityNotFoundException e) {
            log.error("Student not found with ID: {}", studentId);
            return new ResponseEntity<>(
                    ApiResponse.error("Student not found with ID: " + studentId),
                    HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            log.error("Error while fetching student with ID {}: ", studentId, e);
            return new ResponseEntity<>(
                    ApiResponse.error("Error fetching student: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
/*
@PostMapping
    public ResponseEntity<ApiResponse<Student>> createStudent(@RequestBody Student student) {
        log.info("Creating new student with ID: {}", student.getStudentId());
        try {
            Student createdStudent = studentService.createStudent(student);
            log.info("Successfully created student with ID: {}", createdStudent.getStudentId());
            return new ResponseEntity<>(
                    ApiResponse.success("Student created successfully", createdStudent),
                    HttpStatus.CREATED
            );
        } catch (IllegalArgumentException e) {
            log.error("Error creating student: {}", e.getMessage());
            return new ResponseEntity<>(
                    ApiResponse.error("Cannot create student: " + e.getMessage()),
                    HttpStatus.BAD_REQUEST
            );
        } catch (Exception e) {
            log.error("Unexpected error while creating student: ", e);
            return new ResponseEntity<>(
                    ApiResponse.error("Error creating student: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

* */

    @PutMapping("/{studentId}")
    public ResponseEntity<ApiResponse<Student>> updateStudent(
            @PathVariable String studentId,
            @RequestBody Student student) {
        log.info("Updating student with ID: {}", studentId);
        try {
            Student updatedStudent = studentService.updateStudent(studentId, student);
            log.info("Successfully updated student with ID: {}", studentId);
            return ResponseEntity.ok(ApiResponse.success(
                    "Student updated successfully",
                    updatedStudent
            ));
        } catch (EntityNotFoundException e) {
            log.error("Student not found with ID: {}", studentId);
            return new ResponseEntity<>(
                    ApiResponse.error("Cannot update student: " + e.getMessage()),
                    HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            log.error("Error updating student with ID {}: ", studentId, e);
            return new ResponseEntity<>(
                    ApiResponse.error("Error updating student: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(@PathVariable String studentId) {
        log.info("Deleting student with ID: {}", studentId);
        try {
            studentService.deleteStudent(studentId);
            log.info("Successfully deleted student with ID: {}", studentId);
            return ResponseEntity.ok(ApiResponse.success(
                    "Student deleted successfully",
                    null
            ));
        } catch (EntityNotFoundException e) {
            log.error("Student not found with ID: {}", studentId);
            return new ResponseEntity<>(
                    ApiResponse.error("Cannot delete student: " + e.getMessage()),
                    HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            log.error("Error deleting student with ID {}: ", studentId, e);
            return new ResponseEntity<>(
                    ApiResponse.error("Error deleting student: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
