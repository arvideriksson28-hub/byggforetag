package com.example.byggforetag.Model;

import com.example.byggforetag.Enums.JobRole;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "job_assignment")
public class JobAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Enumerated(EnumType.STRING)
    @Column(name = "job_role", nullable = false)
    private JobRole jobRole;

    @Column(name = "assigned_date", nullable = false)
    private LocalDate assignedDate;

    public JobAssignment(Job job, Employee employee, JobRole jobRole, LocalDate assignedDate) {
        this.job = job;
        this.employee = employee;
        this.jobRole = jobRole;
        this.assignedDate = assignedDate;
    }

    public JobAssignment(){}

    public Long getId() {
        return id;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public JobRole getJobRole() {
        return jobRole;
    }

    public void setJobRole(JobRole jobRole) {
        this.jobRole = jobRole;
    }

    public LocalDate getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(LocalDate assignedDate) {
        this.assignedDate = assignedDate;
    }
}
