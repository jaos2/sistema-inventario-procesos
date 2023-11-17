package com.sistema.inventario.service;

import com.sistema.inventario.controller.AuthResponse;
import com.sistema.inventario.controller.LoginRequest;
import com.sistema.inventario.controller.RegisterRequest;
import com.sistema.inventario.exceptions.NotFoundException;
import com.sistema.inventario.model.UserModel;
import com.sistema.inventario.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.NotActiveException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    public AuthResponse login(LoginRequest request) {

        Optional<UserModel> userdb=userRepository.findByEmail(request.getEmail());
        if (userdb.isEmpty()){
            throw new NotFoundException("email o password invalido");
        }
        if (!passwordEncoder.matches(request.getPassword(), userdb.get().getPassword())){
            throw new NotFoundException("email o password invalido");
        }
        return AuthResponse.builder()
                .token(jwtService.getToken(userdb.get()))
                .build();
    }

    public AuthResponse register(RegisterRequest request) {

       UserModel user = UserModel.builder()
               .firstName(request.getFirstName())
               .lastName(request.getLastName())
               .email(request.getEmail())
               .phone(request.getPhone())
               .password(passwordEncoder.encode(request.getPassword()))
               .document(request.getDocument())
               .build();
       userRepository.save(user);

       return AuthResponse.builder()
               .token(jwtService.getToken(user))
               .build();
    }
}
