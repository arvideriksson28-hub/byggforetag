package com.example.byggforetag.Model;

import com.example.byggforetag.Enums.QuoteStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "quote")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    private Job job;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private QuoteStatus quoteStatus;

    @Column(name = "travel_fee", nullable = false)
    private BigDecimal travelFee;

    @Column(name = "rot_deduction", nullable = false)
    private BigDecimal rotDeduction;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    public Quote(){}

    public Quote(Job job, QuoteStatus quoteStatus, BigDecimal travelFee, BigDecimal rotDeduction, BigDecimal totalPrice) {
        this.job = job;
        this.quoteStatus = quoteStatus;
        this.travelFee = travelFee;
        this.rotDeduction = rotDeduction;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public QuoteStatus getQuoteStatus() {
        return quoteStatus;
    }

    public void setQuoteStatus(QuoteStatus quoteStatus) {
        this.quoteStatus = quoteStatus;
    }

    public BigDecimal getTravelFee() {
        return travelFee;
    }

    public void setTravelFee(BigDecimal travelFee) {
        this.travelFee = travelFee;
    }

    public BigDecimal getRotDeduction() {
        return rotDeduction;
    }

    public void setRotDeduction(BigDecimal rotDeduction) {
        this.rotDeduction = rotDeduction;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
