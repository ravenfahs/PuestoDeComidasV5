package com.restaurante.reviews.repository;

import com.restaurante.reviews.models.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends UserRepository<Client,Long> {
}
