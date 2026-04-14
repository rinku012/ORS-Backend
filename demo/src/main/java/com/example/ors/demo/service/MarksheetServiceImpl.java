package com.example.ors.demo.service;

import com.example.ors.demo.dao.MarksheetRepository;
import com.example.ors.demo.dto.MarksheetDTO;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class MarksheetServiceImpl implements MarksheetServiceInt{

    @Autowired
    private MarksheetRepository repository;

    @Override
    public long add(MarksheetDTO dto) {
        return repository.save(dto).getId();
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public MarksheetDTO findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public MarksheetDTO findByRollNo(String rollNo) {
        return repository.findByRollNo(rollNo).orElse(null);
    }

    @Override
    public List<MarksheetDTO> list() {
        return repository.findAll();
    }

    @Override
    public List<MarksheetDTO> getMeritList() {
        // Fetch all, then sort by (physics + chemistry + maths) descending
        List<MarksheetDTO> list = repository.findAll();
        
        return list.stream()
            .sorted((m1, m2) -> {
                int total1 = m1.getPhysics() + m1.getChemistry() + m1.getMaths();
                int total2 = m2.getPhysics() + m2.getChemistry() + m2.getMaths();
                return Integer.compare(total2, total1); // Descending order
            })
            .limit(10) // Only top 10
            .collect(Collectors.toList());
    }
}


