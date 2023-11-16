package com.sistema.inventario.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Primer nombre es requerido")
    @Size(min= 1, max = 100, message = "El primer nombre debe tener al menos de 1 a 100 caracteres")
    private String firstName;

    @NotBlank(message = "El apellido es requerido")
    @Size(min= 1, max = 100, message = "El apellido debe tener al menos de 1 a 100 caracteres")
    private String lastName;

    @NotBlank(message = "Email es requerido")
    @Email(message = "Email debe ser valido")
    private String email;

    @NotBlank(message = "Telefono requerido")
    @Size(min= 1, max = 20, message = "El telefono debe tener al menos de 1 a 20 caracteres")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "El número de teléfono debe ser un número válido de 10 digitos")
    private String phone;

    @NotBlank(message = "Contraseña requerida")
    @Size(min= 8, max = 20, message = "La contraseña debe tener de 8 a 20 caracteres")
    private String password;

    @NotBlank(message = "Numero de documento requerido")
    @Size(min= 5, max = 20, message = "el documento debe tener de 5 a 20 caracteres")
    @Column(unique = true)
    private String document;


}
