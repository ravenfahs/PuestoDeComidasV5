package atl.bootcamp.e9.savorspot.DTO.ReviewsDTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewDTO {
        private Long id;
        private String commentary;
        private int rating;
        private LocalDateTime dateTimeReviews;
        private ReviewsFootStallDTO reviewsFootStallDTO;
        private ReviewsClientDTO reviewsClientDTO;

}
