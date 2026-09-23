package com.example.byggforetag.Controller;

import com.example.byggforetag.DTO.ServiceTypeResponseDto;
import com.example.byggforetag.Service.ServiceTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/servicetypes")
public class ServiceTypeController {
    private final ServiceTypeService serviceTypeService;

    public ServiceTypeController(ServiceTypeService serviceTypeService) {
        this.serviceTypeService = serviceTypeService;
    }

    @GetMapping()
    public ResponseEntity<List<ServiceTypeResponseDto>> getAllServiceTypes(){
        return ResponseEntity.ok(serviceTypeService.getAllServiceTypes());
    }
}
