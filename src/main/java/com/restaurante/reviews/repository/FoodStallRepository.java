package com.restaurante.reviews.repository;

import com.restaurante.reviews.models.FoodStall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodStallRepository extends JpaRepository<FoodStall,Long> {

}

