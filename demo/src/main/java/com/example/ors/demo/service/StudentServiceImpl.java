package com.example.ors.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ors.demo.dto.StudentDTO;
import com.example.ors.demo.dao.StudentRepository; // Adjust package name to your project
import com.example.ors.demo.dao.CollegeRepository;

@Service
@Transactional
public class StudentServiceImpl implements StudentServiceInt{
    @Autowired
    private StudentRepository repository;

    @Autowired
    private CollegeRepository collegeRepository;

    @Override
    public long add(StudentDTO dto) throws Exception {
        // 1. Business Logic: Check for duplicate Email
        StudentDTO existDto = repository.findByEmail(dto.getEmail());
        if (existDto != null) {
            throw new RuntimeException("Email ID already exists!");
        }

        // 2. Optional: Fetch College Name automatically if only ID is provided
        if (dto.getCollegeId() > 0) {
            collegeRepository.findById(dto.getCollegeId()).ifPresent(college -> {
                dto.setCollegeName(college.getName());
            });
        }

        return repository.save(dto).getId();
    }

    @Override
    public void update(StudentDTO dto) throws Exception {
        // Check if student exists before updating
        if (!repository.existsById(dto.getId())) {
            throw new RuntimeException("Student record not found!");
        }
        repository.save(dto);
    }

    @Override
    public void delete(long id) throws Exception {
        repository.deleteById(id);
    }

    @Override
    public StudentDTO findById(long id) {
        Optional<StudentDTO> dto = repository.findById(id);
        return dto.orElse(null);
    }

    @Override
    public StudentDTO findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public List<StudentDTO> search(StudentDTO dto) {
        // For basic ORS logic, return all. 
        // You can later add Pagination or Criteria search here.
        return repository.findAll();
    }
}


