package atl.bootcamp.e9.savorspot.DTO.ReviewsDTO;


import lombok.Data;

@Data
public class ReviewsIdsDTO {
    private Long idClient;
    private Long idFoodStall;
    private Long idFood;
    private String commentary;
    private int rating;

}
