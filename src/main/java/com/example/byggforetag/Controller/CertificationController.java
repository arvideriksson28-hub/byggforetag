package com.example.byggforetag.Controller;

import com.example.byggforetag.DTO.CertificationDto;
import com.example.byggforetag.Service.CertificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/certifications")
public class CertificationController {
    private final CertificationService certificationService;

    public CertificationController(CertificationService certificationService) {
        this.certificationService = certificationService;
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<CertificationDto>> getCertificationsByEmployeeId(@PathVariable Long employeeId){
        return ResponseEntity.ok(certificationService.getCertificationByEmployeeId(employeeId));
    }
}
