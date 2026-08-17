package com.example.byggforetag.Controller;

import com.example.byggforetag.DTO.*;
import com.example.byggforetag.Enums.JobStatus;
import com.example.byggforetag.Enums.LeaveStatus;
import com.example.byggforetag.Service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UserService userService;
    private final JobService jobService;
    private final EmployeeService employeeService;
    private final JobAssignmentService jobAssignmentService;
    private final LeaveRequestService leaveRequestService;
    private final TimeReportService timeReportService;

    public AdminController(UserService userService, JobService jobService, EmployeeService employeeService, JobAssignmentService jobAssignmentService, LeaveRequestService leaveRequestService, TimeReportService timeReportService) {
        this.userService = userService;
        this.jobService = jobService;
        this.employeeService = employeeService;
        this.jobAssignmentService = jobAssignmentService;
        this.leaveRequestService = leaveRequestService;
        this.timeReportService = timeReportService;

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

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto){
        return ResponseEntity.ok(employeeService.updateEmployee(employeeDto, id));
    }

    @PostMapping("/jobassignments/assignEmployee")
    public ResponseEntity<JobAssignmentDto> assignEmployee(@RequestBody JobAssignmentDto jobAssignmentDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(jobAssignmentService.assignEmployee(jobAssignmentDto));
    }

    @DeleteMapping("/jobassignments/{id}")
    public ResponseEntity<Void> removeEmployee(@PathVariable Long id){
        jobAssignmentService.removeEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/jobassignments/{id}")
    public ResponseEntity<List<JobAssignmentDto>> getAllJobAssignments(@PathVariable Long id){
        return ResponseEntity.ok(jobAssignmentService.getJobAssignmentByJobId(id));
    }

    @GetMapping("/leaverequests")
    public ResponseEntity<List<LeaveRequestDto>> getAllLeaveRequests(){
        return ResponseEntity.ok(leaveRequestService.getAllLeaveRequests());
    }

    @PutMapping("/leaverequests/{id}/status")
    public ResponseEntity<LeaveRequestDto> answerLeaveRequest(@PathVariable Long id, @RequestBody LeaveStatus leaveStatus){
        return ResponseEntity.ok(leaveRequestService.updateLeaveStatus(id, leaveStatus));
    }

    @GetMapping("/timereports")
    public ResponseEntity<List<TimeReportDto>> getAllTimeReports(){
        return ResponseEntity.ok(timeReportService.getAllTimeReports());
    }

    @GetMapping("/timereports/job/{jobId}")
    public ResponseEntity<List<TimeReportDto>> getTimeReportsByJob(@PathVariable Long jobId){
        return ResponseEntity.ok(timeReportService.getTimeReportsForJob(jobId));
    }

}
