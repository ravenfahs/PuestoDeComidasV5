package atl.bootcamp.e9.savorspot.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FoodOnOrderDTO {
    private Long idFood;
    private int quantity;
}
