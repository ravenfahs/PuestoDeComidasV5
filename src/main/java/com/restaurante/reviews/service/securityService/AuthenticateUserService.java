package com.restaurante.reviews.service.securityService;

import com.restaurante.reviews.DTO.AuthUserDTO;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticateUserService {

    void getUserByCredentials(AuthUserDTO authUserDTO, HttpServletResponse response);
}
