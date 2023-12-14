package com.sistema.inventario.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @Email(message = "Email should be valid")
    @Column(unique = true, nullable = false)
    private String email;
    @NotNull(message = "Password is required")
    @Size(min = 8, max = 255,message = "password min 8 characters and max 255")
    private String password;
}
