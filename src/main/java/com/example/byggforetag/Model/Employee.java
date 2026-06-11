package com.example.byggforetag.Model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "employee", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<JobAssignment> jobAssignments = new ArrayList<>();

    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @Column(name = "vacation_days")
    private Integer vacationDays;

    @OneToMany(mappedBy = "employee", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<LeaveRequest> leaveRequests = new ArrayList<>();

    public Employee(){}

    public Employee(User user, List<JobAssignment> jobAssignments, LocalDate hireDate) {
        this.user = user;
        this.jobAssignments = jobAssignments;
        this.hireDate = hireDate;
        this.vacationDays = 25;
    }

    public Long getId() {
        return id;
    }

    public List<LeaveRequest> getLeaveRequests() {
        return leaveRequests;
    }

    public void setLeaveRequests(List<LeaveRequest> leaveRequests) {
        this.leaveRequests = leaveRequests;
    }

    public List<JobAssignment> getJobAssignments() {
        return jobAssignments;
    }

    public void setJobAssignments(List<JobAssignment> jobAssignments) {
        this.jobAssignments = jobAssignments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
