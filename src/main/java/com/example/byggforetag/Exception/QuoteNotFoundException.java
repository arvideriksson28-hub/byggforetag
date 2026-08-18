package com.example.byggforetag.Exception;

public class QuoteNotFoundException extends RuntimeException {
    public QuoteNotFoundException(Long id) {
        super("Hittade ingen Quote med ID: " + id);
    }
}
