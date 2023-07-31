package com.restaurante.reviews.repository;

import com.restaurante.reviews.models.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente,Long> {
}
