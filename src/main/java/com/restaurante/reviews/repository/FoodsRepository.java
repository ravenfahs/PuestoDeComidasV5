package com.restaurante.reviews.repository;

import com.restaurante.reviews.models.Foods;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodsRepository extends CrudRepository<Foods, Long> {
}
