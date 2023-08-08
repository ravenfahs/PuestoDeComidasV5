package com.restaurante.reviews.repository;

import com.restaurante.reviews.models.OrderFoods;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderFoodsRepository extends CrudRepository<OrderFoods, Long> {

    List<OrderFoods>findByOrder_Id(Long id);
}
