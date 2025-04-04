package com.aronno.expensetracking_api.controller;

import com.aronno.expensetracking_api.entity.User;
import com.aronno.expensetracking_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        if (user.getPassword() == null) {
            return ResponseEntity.status(400).body(null);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(201).body(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String email, @RequestParam String password) {
        User user = userService.getUserByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.ok("User login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
}
