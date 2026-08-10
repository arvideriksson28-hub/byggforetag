package com.example.byggforetag.Exception;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException(Long id)
    {
        super("Jobb hittades inte med id: " + id);
    }
}
