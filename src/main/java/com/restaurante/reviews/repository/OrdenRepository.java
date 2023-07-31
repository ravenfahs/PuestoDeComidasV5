package com.restaurante.reviews.repository;

import com.restaurante.reviews.models.Orden;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepository extends CrudRepository<Orden,Long> {
}
