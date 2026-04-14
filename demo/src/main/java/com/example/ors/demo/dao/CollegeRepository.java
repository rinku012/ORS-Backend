package com.example.ors.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ors.demo.dto.CollegeDTO;

public interface CollegeRepository extends JpaRepository<CollegeDTO,Long>{
    // JpaRepository already provides: save(), findAll(), findById(), delete()
    
    // Custom query to find a college by name if needed
    CollegeDTO findByName(String name);

}
