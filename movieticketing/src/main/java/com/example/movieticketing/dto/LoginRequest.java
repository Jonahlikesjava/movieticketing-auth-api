package com.example.movieticketing.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
public class LoginRequest {
    /**
     * DTO for login requests using either email or username.
     */
    @Setter
    @NotBlank(message = "Email or username is required")
    private String identifier;

    @NotBlank(message = "Password is required")
    private String password;

}


