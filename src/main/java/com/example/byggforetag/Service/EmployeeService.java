package com.example.byggforetag.Service;

import com.example.byggforetag.DTO.EmployeeRequestDto;
import com.example.byggforetag.DTO.EmployeeResponseDto;
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

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public EmployeeResponseDto getEmployeeById(Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->new EmployeeNotFoundException(id));
        return EmployeeResponseDto.fromEntity(employee);

    }

    @Transactional
    public List<EmployeeResponseDto> getAllEmployees(){
        return employeeRepository.findAll().stream()
                .map(EmployeeResponseDto::fromEntity)
                .toList();
    }

    public EmployeeResponseDto updateEmployee(EmployeeRequestDto employeeRequestDto, Long id){
        Employee employee = employeeRepository.findByUserId(id)
                .orElseThrow(()-> new  EmployeeNotFoundException(id));

        if (employeeRequestDto.getHireDate() != null){
            employee.setHireDate(employeeRequestDto.getHireDate());
        }
        if (employeeRequestDto.getVacationDays() != null){
            employee.setVacationDays(employeeRequestDto.getVacationDays());
        }
        return EmployeeResponseDto.fromEntity(employeeRepository.save(employee));
    }

}
