package com.restaurante.reviews.DTO;

import com.restaurante.reviews.models.Client;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderDTO {
    private Long id;
    private String state;
    private LocalDateTime dateTime;
    private String timeDelivery;
    private double total;
    private ClientDTOBasico client;
    @Setter
    private List<OrderFoodsDTO> foods;

    public OrderDTO(Long id, String state, LocalDateTime dateTime, String timeDelivery, double total, ClientDTOBasico client) {
        this.id = id;
        this.state = state;
        this.dateTime = dateTime;
        this.timeDelivery = timeDelivery;
        this.total = total;
        this.client = client;
    }
}
