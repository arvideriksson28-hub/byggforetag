package com.example.byggforetag.DTO;

import com.example.byggforetag.Model.Job;
import com.example.byggforetag.Model.Review;
import com.example.byggforetag.Model.User;

import java.time.LocalDate;

public class ReviewDto {

    private Integer rating;
    private String comment;
    private LocalDate createdAt;

    public ReviewDto() {
    }

    public ReviewDto(Integer rating, String comment, LocalDate createdAt) {
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public static ReviewDto fromEntity(Review review){
        return new ReviewDto(
                review.getRating(),
                review.getComment(),
                review.getCreatedAt()
        );
    }

    public Review toEntity(Job job, User user){
        return new Review(
                job,
                user,
                this.rating,
                this.comment,
                this.createdAt
        );
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
