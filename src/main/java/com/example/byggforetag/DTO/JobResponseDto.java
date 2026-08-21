package com.example.byggforetag.DTO;

import com.example.byggforetag.Enums.JobStatus;
import com.example.byggforetag.Model.Job;
import com.example.byggforetag.embeddable.Address;

import java.time.LocalDate;
import java.util.List;

public class JobResponseDto {
    private Long id;
    private JobStatus jobStatus;
    private Address address;
    private LocalDate scheduledDate;
    private List<JobItemDto> jobItems;

    public JobResponseDto() {
    }

    public JobResponseDto(Long id, JobStatus jobStatus, Address address, LocalDate scheduledDate, List<JobItemDto> jobItems) {
        this.id = id;
        this.jobStatus = jobStatus;
        this.address = address;
        this.scheduledDate = scheduledDate;
        this.jobItems = jobItems;
    }

    public static JobResponseDto fromEntity(Job job){
        return new JobResponseDto(
                job.getId(),
                job.getJobStatus(),
                job.getAddress(),
                job.getScheduledDate(),
                job.getJobItems().stream().map(JobItemDto::fromEntity).toList()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public List<JobItemDto> getJobItems() {
        return jobItems;
    }

    public void setJobItems(List<JobItemDto> jobItems) {
        this.jobItems = jobItems;
    }
}
