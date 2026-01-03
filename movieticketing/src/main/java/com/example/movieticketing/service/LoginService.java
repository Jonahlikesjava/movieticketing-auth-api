package com.example.movieticketing.service;

import com.example.movieticketing.dto.LoginRequest;
import com.example.movieticketing.dto.LoginResponse;
import com.example.movieticketing.entity.UserEntity;
import com.example.movieticketing.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(LoginRequest request) {
        String identifier = request.getIdentifier().trim();
        String rawPassword = request.getPassword();

        // Find user by email or username
        UserEntity user =
                userRepository.findByEmail(identifier)
                        .orElseGet(() ->
                                userRepository.findByUsername(identifier)
                                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"))
                        );

        // Verify password
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        // Build response DTO
        return new LoginResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }
}
