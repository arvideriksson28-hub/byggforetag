package com.example.byggforetag.Exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {

        super("Email redan registrerad: " + email);
    }
}
