package com.example.byggforetag.Exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id)
    {
        super("Hittade ingen employee med id: " + id);
    }
}
