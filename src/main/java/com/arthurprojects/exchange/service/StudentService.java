package com.arthurprojects.exchange.service;

import com.arthurprojects.exchange.dto.CreateStudentRequest;
import com.arthurprojects.exchange.entity.Student;
import com.arthurprojects.exchange.mapper.StudentMapper;
import com.arthurprojects.exchange.repository.StudentRepository;
import com.arthurprojects.exchange.util.StudentIdGenerator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentIdGenerator studentIdGenerator;
    private final StudentMapper studentMapper;

    @Transactional
    public Student createStudent(CreateStudentRequest request) {
        log.info("Creating new student");
        try {
            Student student = studentMapper.toEntity(request);
            log.info("Generated student ID: {}", student.getStudentId());

            Student savedStudent = studentRepository.save(student);
            log.info("Successfully created student with ID: {}", savedStudent.getStudentId());
            return savedStudent;
        } catch (Exception e) {
            log.error("Error creating student: ", e);
            throw new RuntimeException("Error creating student: " + e.getMessage());
        }
    }

    public Student getStudentById(String studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + studentId));
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Transactional
    public Student updateStudent(String studentId, Student studentDetails) {
        Student existingStudent = getStudentById(studentId);

        existingStudent.setFirstName(studentDetails.getFirstName());
        existingStudent.setLastName(studentDetails.getLastName());
        existingStudent.setDateOfBirth(studentDetails.getDateOfBirth());
        existingStudent.setGender(studentDetails.getGender());
        existingStudent.setPlaceOfBirth(studentDetails.getPlaceOfBirth());
        existingStudent.setCountryOfOrigin(studentDetails.getCountryOfOrigin());

        return studentRepository.save(existingStudent);
    }

    @Transactional
    public void deleteStudent(String studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new EntityNotFoundException("Student not found with ID: " + studentId);
        }
        studentRepository.deleteById(studentId);
    }
}
