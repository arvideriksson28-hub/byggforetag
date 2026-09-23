package com.example.byggforetag.Service;

import com.example.byggforetag.DTO.ServiceTypeResponseDto;
import com.example.byggforetag.Repository.ServiceTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTypeService {
    private final ServiceTypeRepository serviceTypeRepository;

    public ServiceTypeService(ServiceTypeRepository serviceTypeRepository) {
        this.serviceTypeRepository = serviceTypeRepository;
    }

    public List<ServiceTypeResponseDto> getAllServiceTypes() {
        return serviceTypeRepository.findAll().stream()
                .map(ServiceTypeResponseDto::fromEntity)
                .toList();
    }
}
