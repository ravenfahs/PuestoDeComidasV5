package com.restaurante.reviews.controllers;

import com.restaurante.reviews.DTO.AuthUserDTO;
import com.restaurante.reviews.service.securityService.AuthenticateUserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/login")
public class LoginController {

    private final AuthenticateUserService authenticateUserService;



    public LoginController(AuthenticateUserService authenticateUserService) {
        this.authenticateUserService = authenticateUserService;
    }

    @PostMapping
    public void login(@RequestBody AuthUserDTO authUserDTO, HttpServletResponse response) {
         authenticateUserService.getUserByCredentials(authUserDTO, response);
    }
}
