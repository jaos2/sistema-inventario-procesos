package com.sistema.inventario.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "address")
public class AddressModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "La direccion es requerida")
    @Size(max = 100, message = "La direccion debe tener un maximo de 100 caracteres")
    private String streetAddress;
    @NotBlank(message = "la ciudad es requerida")
    @Size(max = 100, message = "la ciudad debe tener un maximo de 100 caracteres")
    private String city;
    @NotBlank(message = "el estado es requerido")
    @Size(max = 100, message = "El estado debe tener un maximo de 100 caracteres")
    private String state;
    @NotBlank(message = "Codigo postal requerido")
    @Size(min = 5, max = 10, message = "El codigo postal debe tener un maximo de 100 caracteres")
    private String postalCode;
    @JsonIgnore
    private Boolean status = Boolean.TRUE;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private  UserModel user;

}
