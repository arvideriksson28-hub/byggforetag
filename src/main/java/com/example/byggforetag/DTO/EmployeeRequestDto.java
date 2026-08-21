package com.example.byggforetag.DTO;

import com.example.byggforetag.Model.Employee;

import java.time.LocalDate;
import java.util.List;

public class EmployeeRequestDto {

    private LocalDate hireDate;
    private Integer vacationDays;


    public EmployeeRequestDto() {
    }

    public EmployeeRequestDto(LocalDate hireDate, Integer vacationDays) {
        this.hireDate = hireDate;
        this.vacationDays = vacationDays;
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
}


