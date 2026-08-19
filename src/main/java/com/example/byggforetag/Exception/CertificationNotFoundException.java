package com.example.byggforetag.Exception;

public class CertificationNotFoundException extends RuntimeException {
    public CertificationNotFoundException(Long id) {
        super("Hittade ingen certification med ID: " + id);
    }
}
