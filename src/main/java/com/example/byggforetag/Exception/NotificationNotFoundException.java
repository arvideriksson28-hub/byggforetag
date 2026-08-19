package com.example.byggforetag.Exception;

public class NotificationNotFoundException extends RuntimeException {
    public NotificationNotFoundException(Long id) {
        super("Hittade ingen notification med ID: " + id);
    }
}
