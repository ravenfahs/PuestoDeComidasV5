package com.restaurante.reviews.repository;

import com.restaurante.reviews.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserRepository<T extends User, ID> extends JpaRepository<T, ID> {
}
