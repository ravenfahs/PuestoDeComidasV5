package com.restaurante.reviews.repository;

import com.restaurante.reviews.models.Restaurante;
import org.springframework.data.repository.CrudRepository;

public interface RestauranteRepository extends CrudRepository<Restaurante,Long> {
}
