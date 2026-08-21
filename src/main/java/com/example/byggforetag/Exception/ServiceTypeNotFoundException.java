package com.example.byggforetag.Exception;

public class ServiceTypeNotFoundException extends RuntimeException {
    public ServiceTypeNotFoundException(Long id) {
        super("Hittade ingen ServiceType med ID: " + id);
    }
}
