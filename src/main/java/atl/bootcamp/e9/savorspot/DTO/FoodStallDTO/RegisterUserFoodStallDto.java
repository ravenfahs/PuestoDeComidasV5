package atl.bootcamp.e9.savorspot.DTO.FoodStallDTO;

public record RegisterUserFoodStallDto(
        String fullName, // dueño
        String email,
        String password,
        String foodStallName, // nombre comercial
        String address,
        String typeCuisine,
        String attentionSchedules,
        String phone,
        String facadeImageUrl,
        Double latitude,
        Double longitude
) {}
