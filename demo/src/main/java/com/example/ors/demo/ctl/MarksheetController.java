package com.example.ors.demo.ctl;
import com.example.ors.demo.dto.MarksheetDTO;
import com.example.ors.demo.service.MarksheetServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ORSAPI/Marksheet")
public class MarksheetController {

    @Autowired
    private MarksheetServiceInt service;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody MarksheetDTO dto) {
        long id = service.add(dto);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<MarksheetDTO>> getList() {
        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<MarksheetDTO> get(@PathVariable long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/rollno/{rollNo}")
    public ResponseEntity<MarksheetDTO> getByRollNo(@PathVariable String rollNo) {
        MarksheetDTO dto = service.findByRollNo(rollNo);
        if (dto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/meritlist")
    public ResponseEntity<List<MarksheetDTO>> getMeritList() {
        return new ResponseEntity<>(service.getMeritList(), HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}