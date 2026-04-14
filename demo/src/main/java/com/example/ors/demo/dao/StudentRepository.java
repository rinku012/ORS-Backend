package com.example.ors.demo.dao;

import com.example.ors.demo.dto.StudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentDTO, Long> {
    // Find student by email (useful for unique checks)
    StudentDTO findByEmail(String email);
}