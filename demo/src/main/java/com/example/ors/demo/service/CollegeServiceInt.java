package com.example.ors.demo.service;

import java.util.List;

import com.example.ors.demo.dto.CollegeDTO;

public interface CollegeServiceInt {
    public long add(CollegeDTO dto) throws Exception;
    public void delete(long id) throws Exception;
    public CollegeDTO findById(long id);
    public List<CollegeDTO> search(CollegeDTO dto);
    public void update(CollegeDTO dto) throws Exception;

}
