package com.restaurante.reviews.ordenService;

import com.restaurante.reviews.models.Orden;
import com.restaurante.reviews.models.OrdenComestibles;
import com.restaurante.reviews.models.modeloDTO.OrdenComestibleDTO;
import com.restaurante.reviews.models.modeloDTO.OrdenDTO;
import com.restaurante.reviews.repository.OrdenComesRepository;

import java.util.ArrayList;
import java.util.List;

public class mappearOrdenConComestibles {
    private final OrdenComesRepository ordenComesRepository;

    public mappearOrdenConComestibles(OrdenComesRepository ordenComesRepository) {
        this.ordenComesRepository = ordenComesRepository;
    }

    public List<OrdenDTO> mappearOrden(List<Orden> entidadOrden) throws Exception{

        if (entidadOrden.isEmpty()) throw new Exception("Tabla orden esta vacia");

        List<OrdenDTO> listOrdenesDTO = new ArrayList<>();

        for (Orden orden : entidadOrden) {
            OrdenDTO ordenDTO = new OrdenDTO();

            ordenDTO.setId(orden.getId());
            ordenDTO.setEstado(orden.getEstado());
            ordenDTO.setFecha_hora(orden.getFechaHora());
            ordenDTO.setTiempoEntrega(orden.getTiempoEntrega());
            ordenDTO.setTotal(orden.getPrecioTotal());
            ordenDTO.setCliente(orden.getCliente());
            ordenDTO.setComidas(comestiblesDeOrden(orden.getId()));

            listOrdenesDTO.add(ordenDTO);
        }
        return listOrdenesDTO;
    }

    private List<OrdenComestibleDTO> comestiblesDeOrden(Long id) throws Exception {

        List<OrdenComestibles> entidadOrdenComestible = ordenComesRepository.findByOrden_Id(id);

        if (entidadOrdenComestible.isEmpty()) throw new Exception("Tabla orden_comestible esta vacia");

        List<OrdenComestibleDTO> listComestibles = new ArrayList<>();

        for (OrdenComestibles ordenComestibles: entidadOrdenComestible) {

            OrdenComestibleDTO ordenComestibleDTO = new OrdenComestibleDTO();

            ordenComestibleDTO.setNombre(ordenComestibles.getComestibles().getNombre());
            ordenComestibleDTO.setDescripcion(ordenComestibles.getComestibles().getDescripcion());
            ordenComestibleDTO.setCantidad(ordenComestibles.getCantidad());
            ordenComestibleDTO.setPrecio(ordenComestibles.getComestibles().getPrecio());

            listComestibles.add(ordenComestibleDTO);
        }

        return listComestibles;
    }
}
