package com.restaurante.reviews.repository;

import com.restaurante.reviews.models.OrdenComestibles;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrdenComesRepository extends CrudRepository<OrdenComestibles, Long> {

    List<OrdenComestibles>findByOrden_Id(Long id);
}
