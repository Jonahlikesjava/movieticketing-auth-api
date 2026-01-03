# Movie Ticketing Authentication API

A Spring Boot REST API that provides user registration and login with secure password hashing.

## Tech Stack
- Java 17
- Spring Boot
- Spring Web
- Spring Security
- Spring Data JPA
- Hibernate
- H2 (in-memory database)
- BCrypt
- Lombok

## Features
- User registration with input validation
- Secure password hashing using BCrypt
- Login using username or email
- RESTful API design
- DTO-based request and response handling

## Endpoints

### Register
POST `/api/auth/register`

### Login
POST `/api/auth/login`

## Running the Project
1. Clone the repository
2. Run the Spring Boot application
3. Test endpoints using Postman
4. Access the H2 console at `/h2-console`

## Notes
This project focuses on backend authentication functionality.