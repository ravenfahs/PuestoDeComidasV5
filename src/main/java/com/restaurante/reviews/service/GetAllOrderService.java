package com.restaurante.reviews.service;

import com.restaurante.reviews.DTO.OrderDTO;
import com.restaurante.reviews.models.Orden;
import com.restaurante.reviews.repository.OrdenComesRepository;
import com.restaurante.reviews.repository.OrdenRepository;

import java.util.List;

public class GetAllOrderService {

    private final OrdenRepository ordenRepository;
    private ListOrdersService listOrdersService;

    public GetAllOrderService(OrdenRepository ordenRepository, OrdenComesRepository orderFoodsRepository) {
        this.ordenRepository = ordenRepository;
        this.listOrdersService = new ListOrdersService(orderFoodsRepository);
    }

    public List<OrderDTO> getAllOrder() {

        List<Orden> modelOrder = (List<Orden>) ordenRepository.findAll();
        return listOrdersService.listOrder(modelOrder);
    }
}
