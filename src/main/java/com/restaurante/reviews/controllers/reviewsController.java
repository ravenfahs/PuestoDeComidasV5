package com.restaurante.reviews.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class reviewsController {

    @GetMapping("api/reviews")
    public String cargar(){
        return "...hola mundo...";
    }
}
