package com.example.byggforetag.Controller;

import com.example.byggforetag.DTO.JobDto;
import com.example.byggforetag.DTO.UserDto;
import com.example.byggforetag.Enums.JobStatus;
import com.example.byggforetag.Service.JobService;
import com.example.byggforetag.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UserService userService;
    private final JobService jobService;

    public AdminController(UserService userService, JobService jobService) {
        this.userService = userService;
        this.jobService = jobService;
    }

    @PostMapping("/register/employee")
    public ResponseEntity<UserDto> registerEmployee(@RequestBody UserDto userDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerEmployee(userDto));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getallUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<JobDto>> getAllJobs(){
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @PutMapping("/jobs/{id}/status")
    public ResponseEntity<JobDto> updateJobStatus(@PathVariable Long id,@RequestBody JobStatus jobStatus){
        return ResponseEntity.ok(jobService.updateJobStatus(id, jobStatus));
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id){
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }
}
