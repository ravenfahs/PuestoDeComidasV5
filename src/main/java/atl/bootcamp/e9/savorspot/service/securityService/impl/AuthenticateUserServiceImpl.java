package atl.bootcamp.e9.savorspot.service.securityService.impl;

import atl.bootcamp.e9.savorspot.service.securityService.AuthenticateUserService;
import atl.bootcamp.e9.savorspot.DTO.AuthUserDTO;
import atl.bootcamp.e9.savorspot.exceptions.AuthenticationErrorException;
import atl.bootcamp.e9.savorspot.models.User;
import atl.bootcamp.e9.savorspot.repository.UserRepository;
import atl.bootcamp.e9.savorspot.security.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateUserServiceImpl implements AuthenticateUserService {

    private final UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;

    public AuthenticateUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void getUserByCredentials(AuthUserDTO authUserDTO, HttpServletResponse response) {

        //permite consultar el usuario por el email
        User authenticatedUser = userRepository.findUserByEmail(authUserDTO.getEmail())
                .orElseThrow(() -> new AuthenticationErrorException("Error when authenticating data"));

        //Obtenemos la contraseña Hasheada del usario
        String passwordHashed = authenticatedUser.getPassword();
        //instanciamos el argon2 para poder verificar las contraseñas
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

        //verificamos que la contraseña en la BD y la que envian para autenticar sean iguales
        if(argon2.verify(passwordHashed, authUserDTO.getPassword())) {
            //generamos el token con él, id y el email
            String token = jwtUtil.create(String.valueOf(authenticatedUser.getId()), authenticatedUser.getEmail());
            //regresamos el token creado por header
            response.addHeader("Authorization", "Bearer " + token);
        }
        else{
            //se lanzará una excepción aquí de autenticación
            throw new AuthenticationErrorException("Error when authenticating data");
        }
    }
}
