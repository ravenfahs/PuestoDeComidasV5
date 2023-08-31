package atl.bootcamp.e9.savorspot.service.reviews;

import atl.bootcamp.e9.savorspot.DTO.ReviewsDTO.ReviewDTO;
import atl.bootcamp.e9.savorspot.models.Reviews;

import java.util.List;

public interface GetAllReviewsService {
    List<Reviews> getAllReviews();
    List<Reviews> getAllReviews2();
    List<ReviewDTO> getReviewDTOS();
}
