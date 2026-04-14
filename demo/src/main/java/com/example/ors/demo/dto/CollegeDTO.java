package com.example.ors.demo.dto;


//import java.sql.Time;
//import java.sql.Date;
//import java.sql.Timestamp;
//import java.util.LinkedHashMap;

//import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.Id;




@Entity
@Table(name = "ST_COLLEGE")
public class CollegeDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", length = 50)
    private String name;

    @Column(name = "ADDRESS", length = 50)
    private String address;

    @Column(name = "STATE", length = 50)
    private String state;

    @Column(name = "CITY", length = 50)
    private String city;

    @Column(name = "PHONE_NO", length = 15)
    private String phoneNo;

    // Default Constructor
    public CollegeDTO() {}

    // Getters and Setters
    public Long getId() { 
        return id;
     }
    public void setId(Long id) {
         this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getPhoneNo() { return phoneNo; }
    public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }
}




