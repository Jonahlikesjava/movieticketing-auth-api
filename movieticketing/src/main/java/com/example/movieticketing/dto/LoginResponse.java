package com.example.movieticketing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO returned after a successful login.
 * Exposes non-sensitive user data to the client.
 */
@Getter
@AllArgsConstructor
public class LoginResponse {

    private Long id;
    private String username;
    private String email;
}

