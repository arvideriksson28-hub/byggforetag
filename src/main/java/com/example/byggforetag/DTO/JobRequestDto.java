package com.example.byggforetag.DTO;


import com.example.byggforetag.Enums.JobStatus;
import com.example.byggforetag.Model.Job;
import com.example.byggforetag.Model.User;
import com.example.byggforetag.embeddable.Address;

import java.time.LocalDate;
import java.util.List;

public class JobRequestDto {

    private Address address;
    private LocalDate scheduledDate;
    private List<JobItemDto> jobItem;

    public JobRequestDto() {
    }

    public JobRequestDto(Address address, LocalDate scheduledDate, List<JobItemDto> jobItem) {
        this.address = address;
        this.scheduledDate = scheduledDate;
        this.jobItem = jobItem;
    }

    public Job toEntity(User user){
        return new Job(
                user,
                JobStatus.RECEIVED,
                this.address,
                this.scheduledDate
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
