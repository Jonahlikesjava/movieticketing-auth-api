package com.example.movieticketing.controller;

import com.example.movieticketing.dto.LoginRequest;
import com.example.movieticketing.dto.RegistrationRequest;
import com.example.movieticketing.dto.RegistrationResponse;
import com.example.movieticketing.service.LoginService;
import com.example.movieticketing.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController // Handles REST requests and returns response bodies (JSON) not views
@RequestMapping("/api/auth")
public class AuthController {

    // Business logic for registration
    private final RegistrationService registrationService;

    // Business logic for login
    private final LoginService loginService;

    @PostMapping("/register")
    public RegistrationResponse register(@Valid @RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @PostMapping("/login")
    public void login(@Valid @RequestBody LoginRequest loginrequest) {
        loginService.login(loginrequest);
    }


}

