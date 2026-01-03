package com.example.movieticketing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO returned after a successful user registration.
 * Excludes sesnsitive fields such as passwords.
 */
@Getter
@AllArgsConstructor
public class RegistrationResponse {

    private String username;
    private String email;
}
