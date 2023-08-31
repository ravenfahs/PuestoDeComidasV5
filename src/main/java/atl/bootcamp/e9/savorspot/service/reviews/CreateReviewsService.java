package atl.bootcamp.e9.savorspot.service.reviews;

import atl.bootcamp.e9.savorspot.DTO.ReviewsDTO.ReviewsIdsDTO;
import org.springframework.http.ResponseEntity;


public interface CreateReviewsService {
   ResponseEntity<String> newReview(ReviewsIdsDTO reviewsIdsDTO) throws Exception;



}
