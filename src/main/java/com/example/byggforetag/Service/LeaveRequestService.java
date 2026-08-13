package com.example.byggforetag.Service;

import com.example.byggforetag.DTO.LeaveRequestDto;
import com.example.byggforetag.Enums.LeaveStatus;
import com.example.byggforetag.Exception.EmployeeNotFoundException;
import com.example.byggforetag.Model.Employee;
import com.example.byggforetag.Model.LeaveRequest;
import com.example.byggforetag.Repository.EmployeeRepository;
import com.example.byggforetag.Repository.LeaveRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveRequestService {
    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository;

    public LeaveRequestService(LeaveRequestRepository leaveRequestRepository, EmployeeRepository employeeRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
        this.employeeRepository = employeeRepository;
    }


    public LeaveRequestDto createLeaveRequest(Long employeeId, LeaveRequestDto leaveRequestDto){
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId));
        LeaveRequest saved = leaveRequestRepository.save(leaveRequestDto.toEntity(employee));
        return LeaveRequestDto.fromEntity(saved);
    }

    public List<LeaveRequestDto> getLeaveRequestsByEmployeeId(Long employeeId){
        return leaveRequestRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new RuntimeException("hittade inge leaveRequests för employeeId: " + employeeId))
                .stream()
                .map(LeaveRequestDto::fromEntity)
                .toList();
    }

    public LeaveRequestDto cancelLeaveRequest(Long id){
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("hittade ingen leaverequest med id: " + id));
        leaveRequest.setLeaveStatus(LeaveStatus.CANCELLED);
        leaveRequestRepository.save(leaveRequest);
        return LeaveRequestDto.fromEntity(leaveRequest);
    }

    public List<LeaveRequestDto> getAllLeaveRequests(){
        return leaveRequestRepository.findAll().stream()
                .map(LeaveRequestDto::fromEntity)
                .toList();
    }

    public LeaveRequestDto updateLeaveStatus(Long id, LeaveStatus leaveStatus){
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("hittade ingen leaverequest med id: " + id));
        leaveRequest.setLeaveStatus(leaveStatus);
        return LeaveRequestDto.fromEntity(leaveRequestRepository.save(leaveRequest));
    }
}
