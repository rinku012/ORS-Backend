package com.example.ors.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.example.ors.demo.dto.UserDTO;

@Repository
public interface UserDaoInt extends JpaRepository<UserDTO,Long>{

    // This allows us to find a user by their Login ID for the login process
    Optional<UserDTO> findByLoginId(String loginId);
    
    // Optional: Find by Email
    Optional<UserDTO> findByEmail(String email);



}
