package com.example.byggforetag.Service;

import com.example.byggforetag.DTO.JobAssignmentDto;
import com.example.byggforetag.DTO.JobDto;
import com.example.byggforetag.Exception.EmployeeNotFoundException;
import com.example.byggforetag.Exception.JobNotFoundException;
import com.example.byggforetag.Model.Employee;
import com.example.byggforetag.Model.Job;
import com.example.byggforetag.Model.JobAssignment;
import com.example.byggforetag.Repository.EmployeeRepository;
import com.example.byggforetag.Repository.JobAssignmentRepository;
import com.example.byggforetag.Repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobAssignmentService {
    private final JobAssignmentRepository jobAssignmentRepository;
    private final EmployeeRepository employeeRepository;
    private final JobRepository jobRepository;

    public JobAssignmentService(JobAssignmentRepository jobAssignmentRepository, EmployeeRepository employeeRepository, JobRepository jobRepository) {
        this.jobAssignmentRepository = jobAssignmentRepository;
        this.employeeRepository = employeeRepository;
        this.jobRepository = jobRepository;
    }

    public List<JobAssignmentDto> getJobAssignmentByJobId(Long id){
        List<JobAssignment> jobAssignments = jobAssignmentRepository.findAllByJobId(id)
                .orElseThrow(() -> new RuntimeException("hittade inga jobassignments med id: " + id));
        return jobAssignments.stream()
                .map(JobAssignmentDto::fromEntity)
                .toList();

    }

    public JobAssignmentDto assignEmployee(JobAssignmentDto jobAssignmentDto){
        Employee employee = employeeRepository.findById(jobAssignmentDto.getEmployeeId())
                .orElseThrow(()-> new EmployeeNotFoundException(jobAssignmentDto.getEmployeeId()));
        Job job = jobRepository.findById(jobAssignmentDto.getJobId())
                .orElseThrow(()-> new JobNotFoundException(jobAssignmentDto.getJobId()));
        JobAssignment saved = jobAssignmentRepository.save(jobAssignmentDto.toEntity(employee, job));
        return JobAssignmentDto.fromEntity(saved);
    }

    public void removeEmployee(Long jobAssignmentId){
        jobAssignmentRepository.deleteById(jobAssignmentId);
    }
}
