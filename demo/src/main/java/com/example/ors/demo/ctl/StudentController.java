package com.example.ors.demo.ctl;

import com.example.ors.demo.dto.*;
import com.example.ors.demo.service.StudentServiceInt;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/ORSAPI/Student")
public class StudentController {

    @Autowired
    private StudentServiceInt studentService;

    // 1. ADD THIS: Public list for all logged-in users (including Students)
    @GetMapping("/list")
    public ResponseEntity<?> list() {
        List<StudentDTO> list = studentService.search(new StudentDTO());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody StudentDTO dto, HttpSession session) {
        UserDTO loggedInUser = (UserDTO) session.getAttribute("user");
        
        // Change: Allow Admin (1) and Faculty (3) to save/update
        if (loggedInUser == null || (loggedInUser.getRoleId() != 1 && loggedInUser.getRoleId() != 3)) {
            return new ResponseEntity<>("Access Denied: Students cannot add/update records", HttpStatus.FORBIDDEN);
        }

        try {
            if (dto.getId() > 0) {
                studentService.update(dto);
                return new ResponseEntity<>("Student updated successfully", HttpStatus.OK);
            } else {
                long id = studentService.add(dto);
                return new ResponseEntity<>("Student registered with ID: " + id, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, HttpSession session) {
        UserDTO loggedInUser = (UserDTO) session.getAttribute("user");
        
        // Strict Check: Only Admin (1) can delete
        if (loggedInUser == null || loggedInUser.getRoleId() != 1) {
            return new ResponseEntity<>("Access Denied: Only Admins can delete students", HttpStatus.FORBIDDEN);
        }
        
        try {
            studentService.delete(id);
            return new ResponseEntity<>("Student Deleted Successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Standard Search and Get (Keep as is)
    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable long id) {
        StudentDTO dto = studentService.findById(id);
        return dto != null ? new ResponseEntity<>(dto, HttpStatus.OK) : new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/search")
public ResponseEntity<?> search(@RequestBody StudentDTO dto) {
    List<StudentDTO> list = studentService.search(dto);
    if (list != null && list.size() > 0) {
        return new ResponseEntity<>(list, HttpStatus.OK);
    } else {
        return new ResponseEntity<>("No records found", HttpStatus.OK);
    }
}
}