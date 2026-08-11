package com.example.byggforetag.DTO;

import com.example.byggforetag.Model.Certification;
import com.example.byggforetag.Model.Employee;

import java.time.LocalDate;
import java.util.List;

public class EmployeeDto {

    private Long userId;
    private LocalDate hireDate;
    private Integer vacationDays;
    private List<CertificationDto> certifications;

    public EmployeeDto() {
    }

    public EmployeeDto(Long userId, LocalDate hireDate, Integer vacationDays, List<CertificationDto> certifications) {
        this.userId = userId;
        this.hireDate = hireDate;
        this.vacationDays = vacationDays;
        this.certifications = certifications;
    }

    public static EmployeeDto fromEntity(Employee employee){
        return new EmployeeDto(
                employee.getUser().getId(),
                employee.getHireDate(),
                employee.getVacationDays(),
                employee.getCertifications().stream()
                        .map(CertificationDto::fromEntity)
                        .toList());
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public Integer getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(Integer vacationDays) {
        this.vacationDays = vacationDays;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<CertificationDto> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<CertificationDto> certifications) {
        this.certifications = certifications;
    }
}
