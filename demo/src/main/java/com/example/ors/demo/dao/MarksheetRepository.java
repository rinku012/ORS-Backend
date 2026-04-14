package com.example.ors.demo.dao;


import com.example.ors.demo.dto.MarksheetDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MarksheetRepository extends JpaRepository<MarksheetDTO, Long> {
   // MarksheetDTO findByRollNo(String rollNo);
    // Custom query to find marksheet by Roll Number
    Optional<MarksheetDTO> findByRollNo(String rollNo);
}
