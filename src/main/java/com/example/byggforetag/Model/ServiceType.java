package com.example.byggforetag.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "service_type")
public class ServiceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 90)
    private String name;

    @Column(name = "base_price", nullable = false)
    private BigDecimal basePrice;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "serviceType", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<JobItem> jobItems = new ArrayList<>();

    public ServiceType(){}

    public ServiceType(String name, BigDecimal basePrice, String description) {
        this.name = name;
        this.basePrice = basePrice;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<JobItem> getJobItems() {
        return jobItems;
    }

    public void setJobItems(List<JobItem> jobItems) {
        this.jobItems = jobItems;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
