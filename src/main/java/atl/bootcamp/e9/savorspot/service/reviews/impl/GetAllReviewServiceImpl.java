package atl.bootcamp.e9.savorspot.service.reviews.impl;

import atl.bootcamp.e9.savorspot.DTO.ReviewsDTO.ReviewDTO;
import atl.bootcamp.e9.savorspot.DTO.ReviewsDTO.ReviewsClientDTO;
import atl.bootcamp.e9.savorspot.DTO.ReviewsDTO.ReviewsFootStallDTO;
import atl.bootcamp.e9.savorspot.models.Reviews;
import atl.bootcamp.e9.savorspot.repository.ReviewRepository;
import atl.bootcamp.e9.savorspot.service.reviews.GetAllReviewsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllReviewServiceImpl implements GetAllReviewsService {
    private final ReviewRepository reviewRepository;


    public GetAllReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;

    }

    @Override
    public List<Reviews> getAllReviews(){

        return (List<Reviews>) reviewRepository.findAll();
    }

    @Override
    public List<Reviews> getAllReviews2() {
        return (List<Reviews>) reviewRepository.findAll();
    }

    public List<ReviewDTO> getReviewDTOS() {
        return getAllReviews().stream()
                .map(reviews -> {
                    ReviewDTO reviewDTO = new ReviewDTO();
                    reviewDTO.setId(reviews.getId());
                    reviewDTO.setCommentary(reviews.getCommentary());
                    reviewDTO.setRating(reviews.getRating());
                    reviewDTO.setDateTimeReviews(reviews.getDateTimeReviews());

                    ReviewsFootStallDTO reviewsFootStallDTO = new ReviewsFootStallDTO();
                    reviewsFootStallDTO.setFoodStallName(reviews.getFoodStall().getFoodStallName());
                    reviewDTO.setReviewsFootStallDTO(reviewsFootStallDTO);

                    ReviewsClientDTO reviewsClientDTO = new ReviewsClientDTO();
                    reviewsClientDTO.setFullName(reviews.getClient().getFullName());
                    reviewsClientDTO.setStatus(String.valueOf(reviews.getClient().getUserStatus()));
                    reviewDTO.setReviewsClientDTO(reviewsClientDTO);
                    return reviewDTO;
                }).collect(Collectors.toList());
    }

}
