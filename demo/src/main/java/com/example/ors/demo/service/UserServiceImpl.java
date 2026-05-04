package com.example.ors.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ors.demo.dao.UserDaoInt;
import com.example.ors.demo.dto.RoleDTO;
import com.example.ors.demo.dto.UserDTO;
import java.util.Optional;
import java.util.List;

@Service
public class UserServiceImpl implements UserServiceInt{
    @Autowired
    private UserDaoInt userDaoInt;
    public UserDTO authenticate(String loginId, String password) {
   // 1. Find the user by Login ID
    // 1. Find the user by Login ID
    Optional<UserDTO> userOpt = userDaoInt.findByLoginId(loginId);
    
    if (userOpt.isPresent()) {
        UserDTO user = userOpt.get();
        
        // 2. Remove ALL whitespace, newlines, and hidden characters (\s matches any whitespace)
        String dbPass = user.getPassword().replaceAll("\\s", "");
        String inputPass = password.replaceAll("\\s", "");

        System.out.println("Cleaned DB Pass: [" + dbPass + "]");
        System.out.println("Cleaned Input Pass: [" + inputPass + "]");

        if (dbPass.equals(inputPass)) {
            System.out.println("Login Success!");
            return user;
        } else {
            System.out.println("Password Mismatch - even after cleaning!");
        }
    } else {
        System.out.println("User NOT found in database: " + loginId);
    }
    return null; 
}
    public UserDTO register(UserDTO user) {// 1. Check if the Login ID already exists
    Optional<UserDTO> existingUser = userDaoInt.findByLoginId(user.getLoginId());
    
    if (existingUser.isPresent()) {
        // 2. If it exists, throw an error that the Controller can catch
        throw new RuntimeException("Login ID already exists! Please choose another.");
    }
    if (user.getRoleId() == null || user.getRoleId() == 0) {
        user.setRoleId(RoleDTO.STUDENT);
    }
    // 3. If it doesn't exist, save the user
    return userDaoInt.save(user);
    }

    public List<UserDTO> getUserList() {
    return userDaoInt.findAll();
}
@Override
    public void delete(long id) {
        try {
            userDaoInt.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("User not found or could not be deleted");
        }
    }@Override
public UserDTO findById(long id) {
    // findById returns an Optional, so we use .orElse(null) 
    // to return the user if found, or null if not.
    return userDaoInt.findById(id).orElse(null);
}
}
