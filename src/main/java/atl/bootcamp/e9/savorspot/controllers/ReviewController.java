package atl.bootcamp.e9.savorspot.controllers;

import atl.bootcamp.e9.savorspot.DTO.ReviewsDTO.ReviewDTO;
import atl.bootcamp.e9.savorspot.DTO.ReviewsDTO.ReviewUpdateDTO;
import atl.bootcamp.e9.savorspot.DTO.ReviewsDTO.ReviewsIdsDTO;
import atl.bootcamp.e9.savorspot.models.Reviews;
import atl.bootcamp.e9.savorspot.security.JWTUtil;
import atl.bootcamp.e9.savorspot.security.ValidateToken;
import atl.bootcamp.e9.savorspot.service.reviews.CreateReviewsService;
import atl.bootcamp.e9.savorspot.service.reviews.DeleteReviewService;
import atl.bootcamp.e9.savorspot.service.reviews.GetAllReviewsService;
import atl.bootcamp.e9.savorspot.service.reviews.UpdateReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {
    private final CreateReviewsService createReviewsService;
    private final GetAllReviewsService getAllReviewsService;
    private final UpdateReviewService updateReviewService;
    private final DeleteReviewService deleteReviewService;

    private final ValidateToken validateToken;

     public ReviewController(CreateReviewsService createReviewsService,
                             GetAllReviewsService getAllReviewsService,
                             UpdateReviewService updateReviewService,
                             DeleteReviewService deleteReviewService,
                             JWTUtil jwtUtil) {

        this.createReviewsService = createReviewsService;
        this.getAllReviewsService = getAllReviewsService;
        this.updateReviewService = updateReviewService;
        this.deleteReviewService = deleteReviewService;
        validateToken = new ValidateToken(jwtUtil);
     }

    @PostMapping("/api/review")
    public ResponseEntity<String> createReview(@RequestHeader(value="Authorization") String token,
                                               @RequestBody ReviewsIdsDTO reviewsIdsDTO) throws Exception{

        if (!validateToken.isValidToken(token)) return null;
        return  createReviewsService.newReview(reviewsIdsDTO);
    }

    @GetMapping("/api/reviews")
    public List<ReviewDTO> getReviewsAll(@RequestHeader(value="Authorization") String token){
        if (!validateToken.isValidToken(token)) return null;
        return getAllReviewsService.getReviewDTOS();
    }

   /* @GetMapping("/api/reviews/2")
    public List<Reviews> getReviewsAll2(@RequestHeader(value="Authorization") String token){
        if (!validateToken.isValidToken(token)) return null;
        return getAllReviewsService.getAllReviews2();

    }*/

    @PutMapping("/api/reviews/{id}")
    public ResponseEntity<String> updateReview(@RequestHeader(value="Authorization") String token,
                                               @RequestBody ReviewUpdateDTO reviewUpdateDTO, @PathVariable Long id) {
        if (!validateToken.isValidToken(token)) return null;
        return updateReviewService.updateReview(reviewUpdateDTO, id);
    }

    @DeleteMapping("/api/reviews/{id}")
    public ResponseEntity<String> deleteReview(@RequestHeader(value="Authorization") String token,@PathVariable Long id){
        if (!validateToken.isValidToken(token)) return null;
        return deleteReviewService.deleteReviews(id);
    }
}
