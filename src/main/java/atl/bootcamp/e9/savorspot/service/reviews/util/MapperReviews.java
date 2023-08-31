package atl.bootcamp.e9.savorspot.service.reviews.util;

import atl.bootcamp.e9.savorspot.DTO.ReviewsDTO.ReviewsIdsDTO;
import atl.bootcamp.e9.savorspot.models.Client;
import atl.bootcamp.e9.savorspot.models.FoodStall;
import atl.bootcamp.e9.savorspot.models.Foods;
import atl.bootcamp.e9.savorspot.models.Reviews;

import java.time.LocalDateTime;

public final class MapperReviews {


    private MapperReviews() { throw new UnsupportedOperationException("A static class cannot be instantiated");
    }

    public static Reviews mapperToReview (Client client, FoodStall foodStall, Foods foods, ReviewsIdsDTO reviewsIdsDTO){

        Reviews reviews = new Reviews();
        reviews.setClient(client);
        reviews.setFood(foods);
        reviews.setFoodStall(foodStall);
        reviews.setCommentary(reviewsIdsDTO.getCommentary());
        reviews.setRating(reviewsIdsDTO.getRating());
        reviews.setDateTimeReviews(LocalDateTime.now());
        return  reviews;
    }
}
