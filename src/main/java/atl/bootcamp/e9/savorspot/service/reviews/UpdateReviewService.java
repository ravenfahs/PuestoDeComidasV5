package atl.bootcamp.e9.savorspot.service.reviews;

import atl.bootcamp.e9.savorspot.DTO.ReviewsDTO.ReviewUpdateDTO;
import org.springframework.http.ResponseEntity;

public interface UpdateReviewService {
    ResponseEntity<String> updateReview(ReviewUpdateDTO reviewUpdateDTO, Long id);
}
