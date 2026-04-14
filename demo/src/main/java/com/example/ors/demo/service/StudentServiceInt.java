package com.example.ors.demo.service;

import com.example.ors.demo.dto.StudentDTO;

//import jakarta.websocket.server.ServerEndpoint;

import java.util.List;

//import org.springframework.stereotype.Service;


public interface StudentServiceInt {
    // Add a new student record
    public long add(StudentDTO dto) throws Exception;

    // Update existing student details
    public void update(StudentDTO dto) throws Exception;

    // Delete a student by their ID
    public void delete(long id) throws Exception;

    // Find a single student by ID (for Edit/View)
    public StudentDTO findById(long id);

    // Search students with optional filters (for the List page)
    public List<StudentDTO> search(StudentDTO dto);

    // Find a student by email (useful for checking duplicates)
    public StudentDTO findByEmail(String email);
}

    
 
