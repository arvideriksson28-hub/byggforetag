package com.example.byggforetag.Controller;

import com.example.byggforetag.DTO.EmployeeRequestDto;
import com.example.byggforetag.DTO.EmployeeResponseDto;
import com.example.byggforetag.Service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> seeProfile(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }
}
