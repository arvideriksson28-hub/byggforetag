package com.example.byggforetag.Service;

import com.example.byggforetag.DTO.TimeReportDto;
import com.example.byggforetag.Exception.EmployeeNotFoundException;
import com.example.byggforetag.Exception.JobNotFoundException;
import com.example.byggforetag.Model.Employee;
import com.example.byggforetag.Model.Job;
import com.example.byggforetag.Model.TimeReport;
import com.example.byggforetag.Repository.EmployeeRepository;
import com.example.byggforetag.Repository.JobRepository;
import com.example.byggforetag.Repository.TimeReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeReportService {
    private final TimeReportRepository timeReportRepository;
    private final EmployeeRepository employeeRepository;
    private final JobRepository jobRepository;

    public TimeReportService(TimeReportRepository timeReportRepository, EmployeeRepository employeeRepository, JobRepository jobRepository) {
        this.timeReportRepository = timeReportRepository;
        this.employeeRepository = employeeRepository;
        this.jobRepository = jobRepository;
    }

    public TimeReportDto createTimeReport(TimeReportDto timeReportDto, Long employeeId, Long jobId){
        Job job = jobRepository.findById(jobId)
                .orElseThrow(()-> new JobNotFoundException(jobId));
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()-> new EmployeeNotFoundException(employeeId));
        return TimeReportDto.fromEntity(timeReportRepository.save(timeReportDto.toEntity(employee, job)));

    }

    public List<TimeReportDto> getTimeReports(Long employeeId){
        return timeReportRepository.findAllByEmployeeId(employeeId).stream()
                .map(TimeReportDto::fromEntity)
                .toList();
    }

    public void deleteTimeReport(Long id){
        TimeReport timeReport = timeReportRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("hittade ingen timeReport med ID: " + id));
        timeReportRepository.delete(timeReport);
    }

    public List<TimeReportDto> getAllTimeReports(){
        return timeReportRepository.findAll().stream()
                .map(TimeReportDto::fromEntity)
                .toList();
    }

    public List<TimeReportDto> getTimeReportsForJob(Long jobId){
        return timeReportRepository.findAllByJobId(jobId).stream()
                .map(TimeReportDto::fromEntity)
                .toList();
    }
}
