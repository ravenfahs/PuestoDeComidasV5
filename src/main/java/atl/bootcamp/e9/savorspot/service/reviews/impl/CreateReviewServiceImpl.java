package atl.bootcamp.e9.savorspot.service.reviews.impl;

import atl.bootcamp.e9.savorspot.DTO.ReviewsDTO.ReviewsIdsDTO;
import atl.bootcamp.e9.savorspot.exceptions.FoodNotFoundException;
import atl.bootcamp.e9.savorspot.exceptions.FoodStallNotFoundException;
import atl.bootcamp.e9.savorspot.exceptions.NotPermitsUserException;
import atl.bootcamp.e9.savorspot.exceptions.UserNotFoundException;
import atl.bootcamp.e9.savorspot.models.Client;
import atl.bootcamp.e9.savorspot.models.FoodStall;
import atl.bootcamp.e9.savorspot.models.Foods;
import atl.bootcamp.e9.savorspot.repository.ClientRepository;
import atl.bootcamp.e9.savorspot.repository.FoodStallRepository;
import atl.bootcamp.e9.savorspot.repository.FoodsRepository;
import atl.bootcamp.e9.savorspot.repository.ReviewRepository;
import atl.bootcamp.e9.savorspot.service.reviews.CreateReviewsService;
import atl.bootcamp.e9.savorspot.service.reviews.util.MapperReviews;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateReviewServiceImpl implements CreateReviewsService {

    private final ClientRepository clientRepository;
    private final FoodStallRepository foodStallRepository;
    private final FoodsRepository foodRepository;
    private final ReviewRepository reviewRepository;

      public CreateReviewServiceImpl(ClientRepository clientRepository, FoodStallRepository foodStallRepository,
                                   FoodsRepository foodRepository, ReviewRepository reviewRepository) {
        this.clientRepository = clientRepository;
        this.foodStallRepository = foodStallRepository;
        this.foodRepository = foodRepository;
        this.reviewRepository = reviewRepository;

    }

    @Override
    public ResponseEntity<String> newReview( ReviewsIdsDTO reviewsIdsDTO)  {

            Client client = clientRepository.findById(reviewsIdsDTO.getIdClient())
                    .orElseThrow(() -> new UserNotFoundException("Client not found with ID: " + reviewsIdsDTO.getIdClient()));

            Foods foods = foodRepository.findById(reviewsIdsDTO.getIdFood())
                    .orElseThrow(() -> new FoodNotFoundException("Food not found with ID: " + reviewsIdsDTO.getIdFood()));

           FoodStall foodStall = foodStallRepository.findById(reviewsIdsDTO.getIdFoodStall())
                .orElseThrow(() -> new FoodStallNotFoundException("Food stall not found with ID: " + reviewsIdsDTO.getIdFoodStall()));

           if (!foods.getFoodStall().getId().equals(foodStall.getId()))
               throw new NotPermitsUserException("Error in the relationship between the food and the food stall it belongs to.");


            reviewRepository.save(MapperReviews.mapperToReview(client, foodStall, foods, reviewsIdsDTO));

            return new ResponseEntity<>("Review successfully created.", HttpStatus.CREATED);
    }
}

