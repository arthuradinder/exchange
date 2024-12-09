package com.arthurprojects.exchange.mapper;

import com.arthurprojects.exchange.dto.CreateStudentRequest;
import com.arthurprojects.exchange.entity.Student;
import com.arthurprojects.exchange.util.StudentIdGenerator;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    private final StudentIdGenerator studentIdGenerator;

    public StudentMapper(StudentIdGenerator studentIdGenerator) {
        this.studentIdGenerator = studentIdGenerator;
    }

    public Student toEntity(CreateStudentRequest request) {
        Student student = new Student();
        student.setStudentId(studentIdGenerator.generateStudentId());
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setGender(request.getGender());
        student.setPlaceOfBirth(request.getPlaceOfBirth());
        student.setCountryOfOrigin(request.getCountryOfOrigin());
        return student;
    }
}
