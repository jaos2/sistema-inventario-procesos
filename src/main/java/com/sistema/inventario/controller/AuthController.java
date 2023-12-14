package com.sistema.inventario.controller;

import com.sistema.inventario.dto.AuthResponse;
import com.sistema.inventario.dto.LoginRequest;
import com.sistema.inventario.model.UserModel;
import com.sistema.inventario.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody UserModel request){
        return ResponseEntity.ok(authService.register(request));
    }

}
