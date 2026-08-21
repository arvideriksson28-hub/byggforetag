package com.example.byggforetag.Exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {

        super("Användaren hittades inte med id: " + id );
    }

    public UserNotFoundException(String email){
        super("Hittade ingen användare med email: " + email);
    }
}
