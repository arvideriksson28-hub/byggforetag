package com.example.byggforetag.DTO;

import com.example.byggforetag.Model.Certification;
import com.example.byggforetag.Model.Employee;

import java.time.LocalDate;

public class CertificationDto {
    private String name;
    private LocalDate issuedDate;
    private LocalDate expiryDate;

    public CertificationDto() {
    }

    public CertificationDto(String name, LocalDate issuedDate, LocalDate expiryDate) {
        this.name = name;
        this.issuedDate = issuedDate;
        this.expiryDate = expiryDate;
    }

    public static CertificationDto fromEntity(Certification certification){
        return new CertificationDto(
                certification.getName(),
                certification.getIssuedDate(),
                certification.getExpiryDate()
        );
    }

    public Certification toEntity(Employee employee){
        return new Certification(
                employee,
                this.getName(),
                this.issuedDate,
                this.expiryDate
        );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}
