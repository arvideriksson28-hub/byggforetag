package com.example.byggforetag.Controller;

import com.example.byggforetag.DTO.ReviewDto;
import com.example.byggforetag.Service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/{jobId}/{userId}")
    public ResponseEntity<ReviewDto> createReview(@PathVariable Long jobId, @PathVariable Long userId, @RequestBody ReviewDto reviewDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.createReview(jobId, userId, reviewDto));
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<ReviewDto> getReviewByJobId(@PathVariable Long jobId){
        return ResponseEntity.ok(reviewService.getReviewsByJobId(jobId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable Long id, @RequestBody ReviewDto reviewDto){
        return ResponseEntity.ok(reviewService.updateReview(id, reviewDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id){
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
