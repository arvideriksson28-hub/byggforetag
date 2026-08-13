package com.example.byggforetag.DTO;

import com.example.byggforetag.Enums.LeaveStatus;
import com.example.byggforetag.Enums.LeaveType;
import com.example.byggforetag.Model.Employee;
import com.example.byggforetag.Model.Job;
import com.example.byggforetag.Model.LeaveRequest;

import java.time.LocalDate;

public class LeaveRequestDto {
    private LeaveType leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaveStatus leaveStatus;

    public LeaveRequestDto() {
    }

    public LeaveRequestDto(LeaveType leaveType, LocalDate startDate, LocalDate endDate, LeaveStatus leaveStatus) {
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveStatus = leaveStatus;
    }

    public static LeaveRequestDto fromEntity(LeaveRequest leaveRequest){
        return new LeaveRequestDto(
                leaveRequest.getLeaveType(),
                leaveRequest.getStartDate(),
                leaveRequest.getEndDate(),
                leaveRequest.getLeaveStatus()
        );
    }

    public LeaveRequest toEntity(Employee employee){
        return new LeaveRequest(
                employee,
                this.leaveType,
                this.startDate,
                this.endDate
        );
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
