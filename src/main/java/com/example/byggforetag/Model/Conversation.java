package com.example.byggforetag.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "conversation")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "job_id", referencedColumnName = "id", nullable = true)
    private Job job;

    @Column(name = "title", nullable = false)
    private String title;

    @OneToMany(mappedBy = "conversation")
    private List<Message> messages = new ArrayList<>();

    public Conversation(){}

    public Conversation(Job job, String title) {
        this.job = job;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
