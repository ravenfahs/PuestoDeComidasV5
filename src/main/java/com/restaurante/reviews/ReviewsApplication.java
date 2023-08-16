package com.restaurante.reviews;

import com.restaurante.reviews.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReviewsApplication {

	@Autowired
	private GetAllOrderByStatusService getAllOrderByStatusService;
	@Autowired
	private GetAllOrderService getAllOrderService;
	@Autowired
	private GetOrderByIdService getOrderService;
	@Autowired
	private UpdateOrderStatusService updateOrderStatusService;
	@Autowired
	private  SoftDeleteOrderStatusService softDeleteOrderStatusService;
	@Autowired
	private CreateOrderService orderService;

	public static void main(String[] args) {
		SpringApplication.run(ReviewsApplication.class, args);
	}

}
