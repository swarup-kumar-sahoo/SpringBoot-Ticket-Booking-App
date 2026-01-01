package com.example.bookevents.service;

import com.example.bookevents.dto.AuthRequest;
import com.example.bookevents.model.User;
import com.example.bookevents.repository.UserRepository;
import com.example.bookevents.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // USER REGISTRATION
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        return userRepository.save(user);
    }

    // ADMIN CREATION
    public User createAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_ADMIN");
        return userRepository.save(user);
    }

    // LOGIN (USER & ADMIN)
    public User loginAndGetUser(AuthRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }

    public String generateToken(User user) {
        return jwtUtil.generateToken(user.getEmail(), user.getRole());
    }
}
