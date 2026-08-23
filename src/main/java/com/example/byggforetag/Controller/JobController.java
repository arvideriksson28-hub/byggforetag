package com.example.byggforetag.Controller;

import com.example.byggforetag.DTO.JobRequestDto;
import com.example.byggforetag.DTO.JobResponseDto;
import com.example.byggforetag.Service.JobService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<JobResponseDto> bookJob(@PathVariable Long userId,@Valid @RequestBody JobRequestDto jobRequestDto){
       return ResponseEntity.status(HttpStatus.CREATED).body(jobService.createJob(userId, jobRequestDto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<JobResponseDto>> seeBookedJobs(@PathVariable Long userId){
        return ResponseEntity.ok(jobService.getJobsByUserId(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponseDto> seeJob(@PathVariable Long id){
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelJob(@PathVariable Long id){
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<JobResponseDto>> getBookedJobs(@PathVariable Long employeeId){
        return ResponseEntity.ok(jobService.getJobsByEmployeeId(employeeId));
    }

}
