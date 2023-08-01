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

    private double totalValorComestibles;

    public CrearOrdenComidaService(ComestiblesRepository comestiblesRepository,
                                                        OrdenComesRepository ordenComesRepository) {

        this.comestiblesRepository = comestiblesRepository;
        this.ordenComesRepository = ordenComesRepository;
        this.totalValorComestibles = 0;
    }

    public void crearOrdenComida(OrdenRequest ordenRequest, Orden nuevaOrden) throws Exception {

        if(ordenRequest.getComidas() == null) throw new Exception();

        for(ComidaEnOrden comidaEnOrden : ordenRequest.getComidas()) {

            OrdenComestibles ordenComestibles = new OrdenComestibles();
            Comestibles comestible = comestiblesRepository.findById(comidaEnOrden.getIdComida()).orElse(null);

            if (comestible != null) {
                ordenComestibles.setOrden(nuevaOrden);
                ordenComestibles.setComestibles(comestible);
                ordenComestibles.setCantidad(comidaEnOrden.getCantidad());
                totalValorComestibles += comestible.getPrecio() * comidaEnOrden.getCantidad();

                ordenComesRepository.save(ordenComestibles);
            }
        }
    }

    public double getTotalValorComestibles() {
        return totalValorComestibles;
    }
}
