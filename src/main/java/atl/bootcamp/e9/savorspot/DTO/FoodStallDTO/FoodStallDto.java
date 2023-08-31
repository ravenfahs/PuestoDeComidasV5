package atl.bootcamp.e9.savorspot.DTO.FoodStallDTO;

public record FoodStallDto(
        Long id,
        String foodStallName,
        String address,
        String typeCuisine,
        String attentionSchedules,
        String phone,
        String facadeImageUrl,
        Double latitude,
        Double longitude
) {}
