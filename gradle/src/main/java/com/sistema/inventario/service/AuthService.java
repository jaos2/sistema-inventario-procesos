package com.sistema.inventario.service;

import com.sistema.inventario.controller.AuthResponse;
import com.sistema.inventario.controller.LoginRequest;
import com.sistema.inventario.controller.RegisterRequest;
import com.sistema.inventario.model.UserModel;
import com.sistema.inventario.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    public AuthResponse login(LoginRequest request) {
        return null;
    }

    public AuthResponse register(RegisterRequest request) {

       UserModel user = UserModel.builder()
               .firstName(request.getFirstName())
               .lastName(request.getLastName())
               .email(request.getEmail())
               .phone(request.getPhone())
               .password(request.getPassword())
               .document(request.getDocument())
               .build();
       userRepository.save(user);

       return AuthResponse.builder()
               .token(jwtService.getToken(user))
               .build();
    }
}
