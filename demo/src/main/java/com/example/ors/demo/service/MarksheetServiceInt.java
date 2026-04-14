package com.example.ors.demo.service;

import com.example.ors.demo.dto.MarksheetDTO;
import java.util.List;
//import com.example.ors.demo.dto.MarksheetDTO;

public interface MarksheetServiceInt {
    public long add(MarksheetDTO dto);
    public void delete(long id);
    public MarksheetDTO findById(long id);
    public MarksheetDTO findByRollNo(String rollNo);
    public List<MarksheetDTO> list();
    public List<MarksheetDTO> getMeritList();

}
