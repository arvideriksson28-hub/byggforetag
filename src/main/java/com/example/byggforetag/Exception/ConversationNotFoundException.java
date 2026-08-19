package com.example.byggforetag.Exception;

public class ConversationNotFoundException extends RuntimeException {
    public ConversationNotFoundException(Long id) {
        super("Hittade ingen conversation med id: " + id);
    }
}
