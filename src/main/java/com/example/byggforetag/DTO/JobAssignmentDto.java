package com.example.byggforetag.DTO;

import com.example.byggforetag.Enums.JobRole;
import com.example.byggforetag.Model.Employee;
import com.example.byggforetag.Model.Job;
import com.example.byggforetag.Model.JobAssignment;

import java.time.LocalDate;

public class JobAssignmentDto {
    private Long employeeId;
    private Long jobId;
    private JobRole jobRole;

    public JobAssignmentDto() {
    }

    public JobAssignmentDto(Long employeeId, Long jobId, JobRole jobRole) {
        this.employeeId = employeeId;
        this.jobId = jobId;
        this.jobRole = jobRole;
    }

    public static JobAssignmentDto fromEntity(JobAssignment jobAssignment){
        return new JobAssignmentDto(
                jobAssignment.getEmployee().getId(),
                jobAssignment.getJob().getId(),
                jobAssignment.getJobRole()
        );
    }

    public JobAssignment toEntity(Employee employee, Job job){
        return new JobAssignment(
                job,
                employee,
                this.jobRole,
                LocalDate.now()
        );
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public JobRole getJobRole() {
        return jobRole;
    }

    public void setJobRole(JobRole jobRole) {
        this.jobRole = jobRole;
    }
}
