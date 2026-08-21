package com.example.byggforetag.DTO;

import com.example.byggforetag.Model.Employee;

import java.time.LocalDate;
import java.util.List;

public class EmployeeResponseDto {

    private Long id;
    private String name;
    private String email;
    private LocalDate hireDate;
    private Integer vacationDays;
    private List<CertificationDto> certifications;

    public EmployeeResponseDto() {
    }

    public EmployeeResponseDto(Long id, String name, String email, LocalDate hireDate, Integer vacationDays, List<CertificationDto> certifications) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.hireDate = hireDate;
        this.vacationDays = vacationDays;
        this.certifications = certifications;
    }

    public static EmployeeResponseDto fromEntity(Employee employee){
        return new EmployeeResponseDto(
                employee.getId(),
                employee.getUser().getName(),
                employee.getUser().getEmail(),
                employee.getHireDate(),
                employee.getVacationDays(),
                employee.getCertifications().stream()
                        .map(CertificationDto::fromEntity)
                        .toList()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<CertificationDto> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<CertificationDto> certifications) {
        this.certifications = certifications;
    }
}
