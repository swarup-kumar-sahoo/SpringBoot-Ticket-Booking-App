package com.example.bookevents.controller;

import com.example.bookevents.dto.AuthRequest;
import com.example.bookevents.dto.AuthResponse;
import com.example.bookevents.model.User;
import com.example.bookevents.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return authService.register(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return authService.getUserById(id);
    }

    @PostMapping("/create-admin")
    public User createAdmin(@RequestBody User user) {
        return authService.createAdmin(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        User user = authService.loginAndGetUser(request);
        String token = authService.generateToken(user);

        return new AuthResponse(
                token,
                user.getRole(),
                user.getId()
        );
    }
}
