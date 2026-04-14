package com.example.ors.demo.dao;
import com.example.ors.demo.dto.CourseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<CourseDTO, Long> {
    CourseDTO findByCourseName(String courseName);
}