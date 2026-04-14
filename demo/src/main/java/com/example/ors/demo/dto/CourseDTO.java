package com.example.ors.demo.dto;

//import java.sql.Time;
//import java.sql.Date;
//import java.sql.Timestamp;


//import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.Id;


@Entity
@Table(name = "ST_COURSE")
public class CourseDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COURSE_NAME", length = 50)
    private String courseName;

    @Column(name = "DESCRIPTION", length = 100)
    private String description;

    @Column(name = "DURATION", length = 20)
    private String duration;

    // Default Constructor
    public CourseDTO() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
}
