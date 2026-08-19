package com.example.byggforetag.Service;

import com.example.byggforetag.DTO.ReviewDto;
import com.example.byggforetag.Exception.JobNotFoundException;
import com.example.byggforetag.Exception.ReviewNotFoundException;
import com.example.byggforetag.Exception.UserNotFoundException;
import com.example.byggforetag.Model.Job;
import com.example.byggforetag.Model.Review;
import com.example.byggforetag.Model.User;
import com.example.byggforetag.Repository.JobRepository;
import com.example.byggforetag.Repository.ReviewRepository;
import com.example.byggforetag.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository, JobRepository jobRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    public ReviewDto createReview(Long jobId, Long userId, ReviewDto reviewDto){
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(userId));
        Job job = jobRepository.findById(jobId).orElseThrow(()-> new JobNotFoundException(jobId));

        Review review = reviewDto.toEntity(job,user);

        return ReviewDto.fromEntity(reviewRepository.save(review));
    }

    public ReviewDto getReviewsByJobId(Long jobId){
        return ReviewDto.fromEntity(reviewRepository.findReviewByJobId(jobId).orElseThrow(()-> new RuntimeException("hittade ingen review med JobId: " + jobId)));
    }

    public ReviewDto updateReview(Long id, ReviewDto reviewDto){
        Review review = reviewRepository.findById(id).orElseThrow(()-> new ReviewNotFoundException(id));

        if (reviewDto.getRating() != null){
            review.setRating(reviewDto.getRating());
        }
        if (reviewDto.getComment() != null){
            review.setComment(reviewDto.getComment());
        }
        if (reviewDto.getCreatedAt() != null){
            review.setCreatedAt(reviewDto.getCreatedAt());
        }
        return ReviewDto.fromEntity(reviewRepository.save(review));
    }

    public void deleteReview(Long id){
        reviewRepository.delete(reviewRepository.findById(id).orElseThrow(()-> new ReviewNotFoundException(id)));
    }

    public List<ReviewDto> getAllReview(){
        return reviewRepository.findAll().stream()
                .map(ReviewDto::fromEntity)
                .toList();
    }

}
