package com.example.byggforetag.DTO;

import com.example.byggforetag.Model.Employee;
import com.example.byggforetag.Model.Job;
import com.example.byggforetag.Model.TimeReport;

import java.time.LocalDateTime;

public class TimeReportDto {

    private Long jobId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long minutes;

    public TimeReportDto() {
    }

    public TimeReportDto(Long jobId, LocalDateTime startTime, LocalDateTime endTime, Long minutes) {
        this.jobId = jobId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.minutes = minutes;
    }

    public TimeReportDto fromEntity(TimeReport timeReport){
        return new TimeReportDto(
                timeReport.getJob().getId(),
                timeReport.getStartTime(),
                timeReport.getEndTime(),
                timeReport.getMinutes()
        );
    }

    public TimeReport toEntity(Employee employee, Job job){
        return new TimeReport(
                job,
                employee,
                this.startTime,
                this.endTime
        );
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getMinutes() {
        return minutes;
    }

    public void setMinutes(Long minutes) {
        this.minutes = minutes;
    }
}
