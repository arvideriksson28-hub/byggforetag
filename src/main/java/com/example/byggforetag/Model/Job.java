package com.example.byggforetag.Model;

import com.example.byggforetag.Enums.JobStatus;
import com.example.byggforetag.embeddable.Address;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "job", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<JobItem> jobItems = new ArrayList<>();

    @OneToMany(mappedBy = "job", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<JobAssignment> jobAssignments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private JobStatus jobStatus;

    @Embedded
    private Address address;

    @Column(name = "scheduled_date", nullable = false)
    private LocalDate scheduledDate;

    @OneToOne(mappedBy = "job")
    private Quote quote;

    @OneToMany(mappedBy = "job", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<TimeReport> timeReports = new ArrayList<>();

    @OneToOne(mappedBy = "job")
    private Conversation conversation;

    @OneToOne(mappedBy = "job")
    private Review review;

    public Job(){}

    public Job(List<JobItem> jobItems, List<JobAssignment> jobAssignments, User user, JobStatus jobStatus, Address address, LocalDate scheduledDate) {
        this.jobItems = jobItems;
        this.jobAssignments = jobAssignments;
        this.user = user;
        this.jobStatus = jobStatus;
        this.address = address;
        this.scheduledDate = scheduledDate;
    }

    public Long getId() {
        return id;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    public void setTimeReports(List<TimeReport> timeReports) {
        this.timeReports = timeReports;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public Quote getQuote() {
        return quote;
    }

    public List<TimeReport> getTimeReports() {
        return timeReports;
    }

    public List<JobItem> getJobItems() {
        return jobItems;
    }

    public void setJobItems(List<JobItem> jobItems) {
        this.jobItems = jobItems;
    }

    public List<JobAssignment> getJobAssignments() {
        return jobAssignments;
    }

    public void setJobAssignments(List<JobAssignment> jobAssignments) {
        this.jobAssignments = jobAssignments;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public Address getAdress() {
        return address;
    }

    public void setAdress(Address address) {
        this.address = address;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }
}
