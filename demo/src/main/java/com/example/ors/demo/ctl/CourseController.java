package com.example.ors.demo.ctl;

import com.example.ors.demo.dto.CourseDTO;
import com.example.ors.demo.dao.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/ORSAPI/Course")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/save")
    public CourseDTO save(@RequestBody CourseDTO dto) {
        return courseRepository.save(dto);
    }

    @GetMapping("/list")
    public List<CourseDTO> list() {
        return courseRepository.findAll();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        courseRepository.deleteById(id);
        return "Course Deleted Successfully";
    }


}
