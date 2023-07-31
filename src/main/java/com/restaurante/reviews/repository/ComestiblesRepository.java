package com.restaurante.reviews.repository;

import com.restaurante.reviews.models.Comestibles;
import org.springframework.data.repository.CrudRepository;

public interface ComestiblesRepository extends CrudRepository<Comestibles, Long> {
}
