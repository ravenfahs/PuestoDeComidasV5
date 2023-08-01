package com.restaurante.reviews.ordenService;

import com.restaurante.reviews.models.Orden;
import com.restaurante.reviews.models.modeloDTO.OrdenDTO;
import com.restaurante.reviews.repository.OrdenComesRepository;
import com.restaurante.reviews.repository.OrdenRepository;

import java.util.ArrayList;
import java.util.List;

public class ObtenerOrdenByIdService {

    private final OrdenRepository ordenRepository;
    private final mappearOrdenConComestibles mappearOrdenConComestibles;

    public ObtenerOrdenByIdService(OrdenRepository ordenRepository, OrdenComesRepository ordenComesRepository) {

        this.ordenRepository = ordenRepository;
        this.mappearOrdenConComestibles = new mappearOrdenConComestibles(ordenComesRepository);
    }

    public OrdenDTO getOrden(Long id) {

        try{

            List<Orden> entidadOrden = new ArrayList<>();
            Orden orden = ordenRepository.findById(id).orElse(null);
            entidadOrden.add(orden);

            return this.mappearOrdenConComestibles.mappearOrden(entidadOrden).get(0);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
