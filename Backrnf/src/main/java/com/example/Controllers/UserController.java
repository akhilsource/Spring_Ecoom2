package com.example.Controllers;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.Entities.User;
import com.example.Entities.UserRepository;
import com.example.Servicies.AuthService;
import com.example.Security.JwttUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;
    @Autowired
    private JwttUtil JwtUtil;

    // Login Endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        System.out.println(loginRequest.getUsername());
        System.out.println(loginRequest.getPassword());
        boolean success = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (success) {
            // Generate a JWT token for the user
            String token = JwtUtil.generateToken(loginRequest.getUsername());
            return ResponseEntity.ok("Login successful! Token: " + token);
        }
        return ResponseEntity.status(401).body("Invalid username or password");
    }

    // Signup Endpoint
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User signupRequest) {
        boolean success = authService.signup(signupRequest.getUsername(), signupRequest.getPassword());
        if (success) {
            return ResponseEntity.ok("User registered successfully!");
        }
        return ResponseEntity.status(401).body("Username already exists!");
    }
    // Get All Users Endpoint (For Testing)
    @GetMapping("/allusers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }
}
