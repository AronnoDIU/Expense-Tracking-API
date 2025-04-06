package com.aronno.expensetracking_api.controller;

import com.aronno.expensetracking_api.entity.User;
import com.aronno.expensetracking_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(
            UserService userService,
            AuthenticationManager authenticationManager
    ) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        if (user.getPassword() == null) {
            return ResponseEntity.status(400).body(null);
        }
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(201).body(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> loginUser(@Valid @RequestBody User loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
