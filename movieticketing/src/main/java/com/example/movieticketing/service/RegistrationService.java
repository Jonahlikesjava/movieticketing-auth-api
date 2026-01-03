package com.example.movieticketing.service;

import com.example.movieticketing.dto.RegistrationRequest;
import com.example.movieticketing.dto.RegistrationResponse;
import com.example.movieticketing.entity.UserEntity;
import com.example.movieticketing.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RegistrationService {

    private final UserRepository userRepository; // inject repo
    private final PasswordEncoder passwordEncoder; // inject passwordEncoder

    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void validateEmailAndUsername(RegistrationRequest request) {

        // normalize null to trim input
        String email = request.getEmail().trim();
        String username = request.getUsername().trim();


        // validate
        if (username.isEmpty() || email.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or email must be entered.");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,"Email already exists"
            );
        }

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Username already exists."
            );
        }

    }

    private void validatePasswordNotSameAsIdentity(String password, String username, String email) {
        if (password.equalsIgnoreCase(username)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password cannot be the same as username.");
        }

        if (password.equalsIgnoreCase(email)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password cannot be the same as email.");
        }
    }


    public RegistrationResponse register(RegistrationRequest request) {

        String password = request.getPassword().trim();
        String username = request.getUsername().trim();
        String email = request.getEmail().trim();

        validateEmailAndUsername(request);
        validatePasswordNotSameAsIdentity(password, username, email);

        // hash a password
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        // save the hashed password
        // and instantiate a new user for when they enter registration details
        UserEntity user = new UserEntity(username, email, hashedPassword);
        userRepository.save(user);

        return new RegistrationResponse(
                user.getUsername(),
                user.getEmail()
        );

    }

}
