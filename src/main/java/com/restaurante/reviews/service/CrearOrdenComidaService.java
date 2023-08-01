package com.restaurante.reviews.service;

import com.restaurante.reviews.models.Comestibles;
import com.restaurante.reviews.models.Orden;
import com.restaurante.reviews.models.OrdenComestibles;
import com.restaurante.reviews.models.models_auxiliar.ComidaEnOrden;
import com.restaurante.reviews.models.models_auxiliar.OrdenRequest;
import com.restaurante.reviews.repository.*;

public class CrearOrdenComidaService {

    private final ComestiblesRepository comestiblesRepository;
    private final OrdenComesRepository ordenComesRepository;

    public CrearOrdenComidaService(ComestiblesRepository comestiblesRepository,
                                                        OrdenComesRepository ordenComesRepository) {

        this.comestiblesRepository = comestiblesRepository;
        this.ordenComesRepository = ordenComesRepository;
    }

    public void crearOrdenComida(OrdenRequest ordenRequest, Orden nuevaOrden) throws Exception {

        if(ordenRequest.getComidas() == null) throw new Exception();

        for(ComidaEnOrden comidaEnOrden : ordenRequest.getComidas()) {

            OrdenComestibles ordenComestibles = new OrdenComestibles();
            Comestibles comestibles = comestiblesRepository.findById(comidaEnOrden.getIdComida()).orElse(null);

            if (comestibles != null) {
                ordenComestibles.setOrden(nuevaOrden);
                ordenComestibles.setComestibles(comestibles);
                ordenComestibles.setCantidad(comidaEnOrden.getCantidad());

                ordenComesRepository.save(ordenComestibles);
            }
        }
    }
}
