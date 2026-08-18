package com.example.byggforetag.DTO;

import com.example.byggforetag.Enums.QuoteStatus;
import com.example.byggforetag.Model.Job;
import com.example.byggforetag.Model.Quote;

import java.math.BigDecimal;

public class QuoteDto {

    private BigDecimal travelFee;
    private BigDecimal rotDeduction;
    private BigDecimal totalPrice;
    private QuoteStatus quoteStatus;

    public QuoteDto() {
    }

    public QuoteDto(BigDecimal travelFee, BigDecimal rotDeduction, BigDecimal totalPrice, QuoteStatus quoteStatus) {
        this.travelFee = travelFee;
        this.rotDeduction = rotDeduction;
        this.totalPrice = totalPrice;
        this.quoteStatus = quoteStatus;
    }

    public static QuoteDto fromEntity(Quote quote){
        return new QuoteDto(
                quote.getTravelFee(),
                quote.getRotDeduction(),
                quote.getTotalPrice(),
                quote.getQuoteStatus()
                );
    }

    public Quote toEntity(Job job){
        return new Quote(
                job,
                QuoteStatus.DRAFT,
                this.travelFee,
                this.rotDeduction,
                this.totalPrice
        );
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

    public QuoteStatus getQuoteStatus() {
        return quoteStatus;
    }

    public void setQuoteStatus(QuoteStatus quoteStatus) {
        this.quoteStatus = quoteStatus;
    }
}
