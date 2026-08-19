package com.example.byggforetag.Exception;

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(Long id) {
        super("Hittade ingen review med ID: " + id);
    }
}
