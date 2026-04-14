package com.example.ors.demo.service;

import com.example.ors.demo.dto.*;
import java.util.List;;

public interface UserServiceInt {
public UserDTO authenticate(String loginId, String password);
    public UserDTO register(UserDTO dto);
    public List<UserDTO> getUserList();
    public void delete(long id); // Critical to match the Controller
    public UserDTO findById(long id); // Needed for the "Edit" fetch
    
}