package com.example.ors.demo.ctl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.example.ors.demo.dto.CollegeDTO;
import com.example.ors.demo.dto.RoleDTO;
import com.example.ors.demo.dto.UserDTO;
import com.example.ors.demo.service.CollegeServiceInt;

import jakarta.servlet.http.HttpSession;
@RestController
@RequestMapping("/ORSAPI/College")
public class CollegeController {
  @Autowired
    private CollegeServiceInt collegeService;

    // 1. SAVE / UPDATE
   @PostMapping("/save")
public ResponseEntity<?> save(@RequestBody CollegeDTO dto, @RequestParam(required = false) Long activeRoleId) {
    // If you aren't using JWT yet, pass roleId from React as a Query Param
    if (activeRoleId == null || activeRoleId != RoleDTO.ADMIN) {
        return new ResponseEntity<>("Access Denied: Only Admins can manage Colleges", HttpStatus.FORBIDDEN);
    }

    try {
        if (dto.getId() != null && dto.getId() > 0) {
            collegeService.update(dto);
            return new ResponseEntity<>("College updated successfully", HttpStatus.OK);
        } else {
            long id = collegeService.add(dto);
            return new ResponseEntity<>("College added with ID: " + id, HttpStatus.OK);
        }
    } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

    // 2. GET BY ID (For viewing details or pre-filling an edit form)
    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable long id) {
        CollegeDTO dto = collegeService.findById(id);
        if (dto != null) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("College not found", HttpStatus.NOT_FOUND);
        }
    }

    // 3. DELETE
    @GetMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id, HttpSession session) {
        UserDTO loggedInUser = (UserDTO) session.getAttribute("user");
        if (loggedInUser == null || loggedInUser.getRoleId() != RoleDTO.ADMIN) {
            return new ResponseEntity<>("Access Denied", HttpStatus.FORBIDDEN);
        }

        try {
            collegeService.delete(id);
            return new ResponseEntity<>("College deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 4. LIST / SEARCH (For your Table view in React)
    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody CollegeDTO dto) {
        List<CollegeDTO> list = collegeService.search(dto);
        if (list != null && list.size() > 0) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No records found", HttpStatus.OK);
        }
    }

    @GetMapping("/list")
public ResponseEntity<?> getList() {
    List<CollegeDTO> list = collegeService.search(new CollegeDTO());
    return new ResponseEntity<>(list, HttpStatus.OK);
}
}