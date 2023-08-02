package com.restaurante.reviews.service;

import com.restaurante.reviews.DTO.OrderDTO;
import com.restaurante.reviews.models.Orden;
import com.restaurante.reviews.repository.OrdenComesRepository;
import com.restaurante.reviews.repository.OrdenRepository;

import java.util.ArrayList;
import java.util.List;

public class GetOrderService {

    private final OrdenRepository ordenRepository;
    private ListOrdersService listOrdersService;

    public GetOrderService(OrdenRepository ordenRepository, OrdenComesRepository orderFoodsRepository) {
        this.ordenRepository = ordenRepository;
        listOrdersService = new ListOrdersService(orderFoodsRepository);
    }

    public OrderDTO getOrderbyId(Long id) {

        List<Orden> modelOrder = new ArrayList<>();
        modelOrder.add(ordenRepository.findById(id).orElse(null));
        return listOrdersService.listOrder(modelOrder).get(0);
    }
}
