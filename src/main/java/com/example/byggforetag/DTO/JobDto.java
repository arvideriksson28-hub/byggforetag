package com.example.byggforetag.DTO;


import com.example.byggforetag.Model.Job;
import com.example.byggforetag.embeddable.Address;

import java.time.LocalDate;
import java.util.List;

public class JobDto {

    private Address address;
    private LocalDate scheduledDate;
    private List<JobItemDto> jobItem;

    public JobDto() {
    }

    public JobDto(Address address, LocalDate scheduledDate, List<JobItemDto> jobItem) {
        this.address = address;
        this.scheduledDate = scheduledDate;
        this.jobItem = jobItem;
    }

    public static JobDto fromEntity(Job job){
        return new JobDto(
                job.getAddress(),
                job.getScheduledDate(),
                job.getJobItems().stream().map(JobItemDto::fromEntity).toList()
        );
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

    public List<JobItemDto> getJobItem() {
        return jobItem;
    }

    public void setJobItem(List<JobItemDto> jobItem) {
        this.jobItem = jobItem;
    }
}
