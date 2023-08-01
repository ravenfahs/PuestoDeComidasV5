package com.restaurante.reviews.service;

import com.restaurante.reviews.models.Orden;
import com.restaurante.reviews.models.OrdenComestibles;
import com.restaurante.reviews.models.modeloDTO.OrdenComestibleDTO;
import com.restaurante.reviews.models.modeloDTO.OrdenDTO;
import com.restaurante.reviews.repository.OrdenComesRepository;
import com.restaurante.reviews.repository.OrdenRepository;

import java.util.ArrayList;
import java.util.List;

public class ObtenerOrdenService {
    private final OrdenRepository ordenRepository;
    private final OrdenComesRepository ordenComesRepository;

    public ObtenerOrdenService(OrdenRepository ordenRepository, OrdenComesRepository ordenComesRepository) {
        this.ordenRepository = ordenRepository;
        this.ordenComesRepository = ordenComesRepository;
    }

    public List<OrdenDTO> getOrdenes()  {

        try {
            List<Orden> entidadOrden = (List<Orden>) ordenRepository.findAll();
            List<OrdenDTO> ordenesDTO = new ArrayList<>();


            for (Orden orden : entidadOrden) {
                OrdenDTO ordenDTO = new OrdenDTO();

                ordenDTO.setId(orden.getId());
                ordenDTO.setEstado(orden.getEstado());
                ordenDTO.setFecha_hora(orden.getFechaHora());
                ordenDTO.setTiempoEntrega(orden.getTiempoEntrega());
                ordenDTO.setTotal(orden.getPrecioTotal());
                ordenDTO.setCliente(orden.getCliente());
                ordenDTO.setComidas(comestiblesDeOrden(orden.getId()));

                ordenesDTO.add(ordenDTO);
            }

            return ordenesDTO;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private List<OrdenComestibleDTO> comestiblesDeOrden(Long id) throws Exception {

        List<OrdenComestibles> entidadOrdenComestible = ordenComesRepository.findByOrden_Id(id);

        if (entidadOrdenComestible.isEmpty()) throw new Exception("Tabla orden_comestible vacia");

        List<OrdenComestibleDTO> listComestibles = new ArrayList<>();

        for (OrdenComestibles ordenComestibles: entidadOrdenComestible) {

            OrdenComestibleDTO ordenComestibleDTO = new OrdenComestibleDTO();
            ordenComestibleDTO.setNombre(ordenComestibles.getComestibles().getNombre());
            ordenComestibleDTO.setDescripcion(ordenComestibles.getComestibles().getDescripcion());
            ordenComestibleDTO.setCantidad(ordenComestibles.getCantidad());

            listComestibles.add(ordenComestibleDTO);
        }

        return listComestibles;
    }
}
