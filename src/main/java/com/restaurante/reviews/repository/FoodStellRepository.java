package com.restaurante.reviews.repository;

import com.restaurante.reviews.models.FoodStall;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodStellRepository extends UserRepository<FoodStall,Long> {
}
