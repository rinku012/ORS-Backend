package com.example.ors.demo.ctl;
import java.util.HashMap;
import java.util.List;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ors.demo.dto.UserDTO;
import com.example.ors.demo.service.UserServiceImpl;
import com.example.ors.demo.dto.RoleDTO;
import java.util.Map;

@RestController
@RequestMapping("/ORSAPI/User")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody UserDTO loginData, HttpSession session) {
    UserDTO user = userServiceImpl.authenticate(loginData.getLoginId(), loginData.getPassword());
    
    // Create a response map to match what your React frontend expects
    Map<String, Object> response = new HashMap<>();

    if (user != null) {
        session.setAttribute("user", user);
        
        response.put("success", true);
        response.put("result", user); // React uses res.data.result
        response.put("message", "Login Successful");
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    } else {
        response.put("success", false);
        response.put("message", "Invalid ID or Password");
        
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}

    // LOGOUT: Invalidates the session
    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return new ResponseEntity<>("Logout Successful", HttpStatus.OK);
    }

    // SIGNUP: Public registration - Always forced to Student (Role 4)
    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody UserDTO user) {
        try {
            user.setRoleId(RoleDTO.STUDENT); // Use the constant 4L
            UserDTO registeredUser = userServiceImpl.register(user);
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // SAVE/UPDATE: Admin only. Handles both adding new users and updating existing ones.
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody UserDTO user, HttpSession session) {
       UserDTO loggedInUser = (UserDTO) session.getAttribute("user");
        
         //Security Check: Only Admin (Role 1) can save/update users
        if (loggedInUser == null || loggedInUser.getRoleId() != RoleDTO.ADMIN) {
            return new ResponseEntity<>("Access Denied: Admin rights required", HttpStatus.FORBIDDEN);
    }

        try {
            UserDTO savedUser = userServiceImpl.register(user); // Logic in service should handle update if ID exists
            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // DELETE: Admin only
    @GetMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id, HttpSession session) {
        UserDTO loggedInUser = (UserDTO) session.getAttribute("user");
        if (loggedInUser == null || loggedInUser.getRoleId() != RoleDTO.ADMIN) {
            return new ResponseEntity<>("Access Denied", HttpStatus.FORBIDDEN);
        }
        
        userServiceImpl.delete(id); // Ensure this method exists in your service
        return new ResponseEntity<>("User Deleted Successfully", HttpStatus.OK);
    }

    // LIST: Returns all users
    @GetMapping("/list")
public ResponseEntity<?> getList() {
    List<UserDTO> list = userServiceImpl.getUserList();
    Map<String, Object> map = new HashMap<>();
    
    if (list != null && !list.isEmpty()) {
        map.put("success", true);
        map.put("result", list); // The frontend usually looks for "result"
    } else {
        map.put("success", false);
        map.put("message", "No records found");
    }
    return ResponseEntity.ok(map);
}
}