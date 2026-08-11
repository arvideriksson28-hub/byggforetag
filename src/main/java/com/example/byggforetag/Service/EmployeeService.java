package com.example.byggforetag.Service;

import com.example.byggforetag.DTO.EmployeeDto;
import com.example.byggforetag.Exception.EmployeeNotFoundException;
import com.example.byggforetag.Model.Employee;
import com.example.byggforetag.Repository.CertificationRepository;
import com.example.byggforetag.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final CertificationRepository certificationRepository;

    public EmployeeService(EmployeeRepository employeeRepository, CertificationRepository certificationRepository) {
        this.employeeRepository = employeeRepository;
        this.certificationRepository = certificationRepository;
    }

    @Transactional
    public EmployeeDto getEmployeeById(Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->new EmployeeNotFoundException(id));
        return EmployeeDto.fromEntity(employee);

    }

    @Transactional
    public List<EmployeeDto> getAllEmployees(){
        return employeeRepository.findAll().stream()
                .map(EmployeeDto::fromEntity)
                .toList();
    }

    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id){
        Employee employee = employeeRepository.findByUserId(id)
                .orElseThrow(()-> new  EmployeeNotFoundException(id));

        if (employeeDto.getHireDate() != null){
            employee.setHireDate(employeeDto.getHireDate());
        }
        if (employeeDto.getVacationDays() != null){
            employee.setVacationDays(employeeDto.getVacationDays());
        }
        return EmployeeDto.fromEntity(employeeRepository.save(employee));
    }

}
