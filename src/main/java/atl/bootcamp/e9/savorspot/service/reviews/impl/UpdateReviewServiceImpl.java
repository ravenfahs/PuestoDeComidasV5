package atl.bootcamp.e9.savorspot.service.reviews.impl;

import atl.bootcamp.e9.savorspot.DTO.ReviewsDTO.ReviewUpdateDTO;
import atl.bootcamp.e9.savorspot.exceptions.ReviewNotFoundException;
import atl.bootcamp.e9.savorspot.models.Reviews;
import atl.bootcamp.e9.savorspot.repository.ReviewRepository;
import atl.bootcamp.e9.savorspot.service.reviews.UpdateReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateReviewServiceImpl implements UpdateReviewService {

    private final ReviewRepository reviewRepository;

    public UpdateReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public ResponseEntity<String> updateReview(ReviewUpdateDTO reviewUpdateDTO, Long id) {

        Reviews reviews = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Client not found with ID: " + id));

        reviews.setCommentary(reviewUpdateDTO.getCommentary());
        reviews.setRating(reviewUpdateDTO.getRating());
        reviewRepository.save(reviews);

        return new ResponseEntity<>("it was updated successfully", HttpStatus.OK);
    }
}
