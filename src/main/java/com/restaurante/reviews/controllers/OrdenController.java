package com.restaurante.reviews.controllers;


import com.restaurante.reviews.DTO.OrdenRequestDTO;
import com.restaurante.reviews.DTO.OrderDTO;
import com.restaurante.reviews.repository.*;
import com.restaurante.reviews.service.CreateOrderService;
import com.restaurante.reviews.service.GetAllOrderService;
import com.restaurante.reviews.service.GetOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrdenController {

    @Autowired
    private ClienteRepository clientRepository;
    @Autowired
    private OrdenRepository ordenRepository;
    @Autowired
    private ComestiblesRepository foodsRepository;
    @Autowired
    private RestauranteRepository foodStellRepository;
    @Autowired
    private OrdenComesRepository ordenFoodsRepository;


    @PostMapping("/api/orden")
    public ResponseEntity<String> registrarOrden(@RequestBody OrdenRequestDTO ordenRequestDTO){

       CreateOrderService orderService = new CreateOrderService(ordenRepository,
                                                                                                clientRepository,
                                                                                                foodsRepository,
                                                                                                foodStellRepository,
                                                                                                ordenFoodsRepository);

        return orderService.createOrden(ordenRequestDTO);
    }

    @GetMapping("/api/orden")
    public List<OrderDTO> getAllOrder(){

        GetAllOrderService getAllOrderService = new GetAllOrderService(ordenRepository,ordenFoodsRepository);

        return getAllOrderService.getAllOrder();
    }

    @GetMapping("/api/orden/{id}")
    public OrderDTO getOrder(@PathVariable Long id) {

        GetOrderService getOrderService = new GetOrderService(ordenRepository, ordenFoodsRepository);

        return getOrderService.getOrderbyId(id);
    }

    /*
    @GetMapping("/api/orden/{id}")
    public OrdenDTO getOrdene(@PathVariable Long id){

        ObtenerOrdenByIdService obtenerOrdenByIdService = new ObtenerOrdenByIdService(ordenRepository,ordenComesRepository);

        return obtenerOrdenByIdService.getOrden(id);
    }*/
}
