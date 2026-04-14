package com.example.ors.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ors.demo.dao.CollegeRepository;
import com.example.ors.demo.dto.CollegeDTO;
import java.util.List;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CollegeServiceImpl implements CollegeServiceInt {
    @Autowired
    private CollegeRepository repository; // Assuming you are using Spring Data JPA

    @Override
    public long add(CollegeDTO dto) throws Exception {
        // Business Logic: Check for duplicate college names
        CollegeDTO existDto = repository.findByName(dto.getName());
        if (existDto != null) {
            throw new RuntimeException("College Name already exists!");
        }
        return repository.save(dto).getId();
    }

    @Override
    public CollegeDTO findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void update(CollegeDTO dto) throws Exception {
        repository.save(dto);
    }

    @Override
    public void delete(long id) throws Exception {
        repository.deleteById(id);
    }

    @Override
    public List<CollegeDTO> search(CollegeDTO dto) {
        // You can implement custom search logic or use repository.findAll()
        return repository.findAll();
    }
}


