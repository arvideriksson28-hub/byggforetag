package com.example.byggforetag.Controller;

import com.example.byggforetag.DTO.JobAssignmentDto;
import com.example.byggforetag.Service.JobAssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobAssignments")
public class JobAssignmentController {

    private final JobAssignmentService jobAssignmentService;

    public JobAssignmentController(JobAssignmentService jobAssignmentService) {
        this.jobAssignmentService = jobAssignmentService;
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<JobAssignmentDto>> getAllJobAssignmentsByEmployeeId(@PathVariable Long employeeId){
        return ResponseEntity.ok(jobAssignmentService.getAllJobAssignmentsByEmployeeId(employeeId));
    }

}
