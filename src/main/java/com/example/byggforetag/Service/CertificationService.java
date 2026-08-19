package com.example.byggforetag.Service;

import com.example.byggforetag.DTO.CertificationDto;
import com.example.byggforetag.Exception.CertificationNotFoundException;
import com.example.byggforetag.Exception.ConversationNotFoundException;
import com.example.byggforetag.Exception.EmployeeNotFoundException;
import com.example.byggforetag.Model.Certification;
import com.example.byggforetag.Model.Employee;
import com.example.byggforetag.Repository.CertificationRepository;
import com.example.byggforetag.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CertificationService {
    private final CertificationRepository certificationRepository;
    private final EmployeeRepository employeeRepository;

    public CertificationService(CertificationRepository certificationRepository, EmployeeRepository employeeRepository) {
        this.certificationRepository = certificationRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<CertificationDto> getCertificationByEmployeeId(Long employeeId){
        return certificationRepository.findAllCertificationByEmployeeId(employeeId).stream()
                .map(CertificationDto::fromEntity)
                .toList();
    }

    public CertificationDto createCertification(Long employeeId, CertificationDto certificationDto){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()-> new EmployeeNotFoundException(employeeId));
        Certification certification = new Certification(
                employee,
                certificationDto.getName(),
                certificationDto.getIssuedDate(),
                certificationDto.getExpiryDate()
        );
        return CertificationDto.fromEntity(certificationRepository.save(certification));
    }

    public void deleteCertification(Long id){
        certificationRepository.delete(certificationRepository.findById(id).orElseThrow(()-> new CertificationNotFoundException(id)));
    }

    public CertificationDto updateCertification(Long id, CertificationDto certificationDto){
        Certification certification = certificationRepository.findById(id)
                .orElseThrow(()-> new CertificationNotFoundException(id));
        if (certificationDto.getName() != null){
            certification.setName(certificationDto.getName());
        }
        if (certificationDto.getIssuedDate() != null){
            certification.setIssuedDate(certificationDto.getIssuedDate());
        }
        if (certificationDto.getExpiryDate() != null){
            certification.setExpiryDate(certificationDto.getExpiryDate());
        }
        return CertificationDto.fromEntity(certificationRepository.save(certification));
    }
}
