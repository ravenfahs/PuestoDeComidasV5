package com.restaurante.reviews.security;


import com.restaurante.reviews.exceptions.InvalidTokenException;
import io.jsonwebtoken.SignatureException;


public  class ValidateToken {

    private final JWTUtil jwtUtil;
    private Long userID;

    public ValidateToken(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public boolean isValidToken(String token) {

        //filtramos que el token no sea nulo y que además venga con el formato correcto
        if(token != null && token.startsWith("Bearer ")) {

            //debemos quitar la frase incial para no tener problemas con el token
            token = token.replace("Bearer ", "");

            try {
                // no retorna el ID, si el token es correcto, de lo contrario lanza una excepción
                String validToken = jwtUtil.getKey(token);

                userID = Long.valueOf(validToken);
                return true;
            }catch(SignatureException e){
                throw new InvalidTokenException(e.getMessage());
            }

        }
        return  false;
    }

    public Long getUserID(){
        return userID;
    }

}
