package com.restaurante.reviews.ordenService;

import com.restaurante.reviews.models.Orden;
import com.restaurante.reviews.models.modeloDTO.OrdenDTO;
import com.restaurante.reviews.repository.OrdenComesRepository;
import com.restaurante.reviews.repository.OrdenRepository;

import java.util.List;

public class ObtenerOrdenesService {
    private final OrdenRepository ordenRepository;
    private final mappearOrdenConComestibles mappearOrdenConComestibles;

    public ObtenerOrdenesService(OrdenRepository ordenRepository, OrdenComesRepository ordenComesRepository) {

        this.ordenRepository = ordenRepository;
        this.mappearOrdenConComestibles = new mappearOrdenConComestibles(ordenComesRepository);
    }

    public List<OrdenDTO> getAllOrdenes() {

        try {

            List<Orden> entidadOrden = (List<Orden>) ordenRepository.findAll();
            return mappearOrdenConComestibles.mappearOrden(entidadOrden);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

