package com.example.byggforetag.Controller;

import com.example.byggforetag.DTO.LeaveRequestDto;
import com.example.byggforetag.Enums.LeaveStatus;
import com.example.byggforetag.Service.LeaveRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaverequests")
public class LeaveRequestController {
    private final LeaveRequestService leaveRequestService;

    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }


    @PostMapping("/{employeeId}")
    public ResponseEntity<LeaveRequestDto> createLeaveRequest(@PathVariable Long employeeId, @RequestBody LeaveRequestDto leaveRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(leaveRequestService.createLeaveRequest(employeeId, leaveRequestDto));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<LeaveRequestDto>> seeLeaveRequests(@PathVariable Long employeeId){
        return ResponseEntity.ok(leaveRequestService.getLeaveRequestsByEmployeeId(employeeId));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<LeaveRequestDto> cancelLeaveRequest(@PathVariable Long id){
        return ResponseEntity.ok(leaveRequestService.cancelLeaveRequest(id));
    }
}
