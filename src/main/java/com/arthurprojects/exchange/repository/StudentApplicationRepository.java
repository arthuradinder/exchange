package com.arthurprojects.exchange.repository;

import com.arthurprojects.exchange.entity.StudentApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentApplicationRepository extends JpaRepository<StudentApplication, String> {
    //Will add custom query methods later on

    boolean existsByPassportNumber(String passportNumber);
    boolean existsByStudentId(String studentId);
}
