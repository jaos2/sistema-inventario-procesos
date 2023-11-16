package com.sistema.inventario.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.math.BigDecimal;

@Entity
@Table(name = "item")
@Data
public class ItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El nombre del articulo no puede estar vacio")
    @Size(min = 3, message = "El articulo debe tener al menos 5 caracteres")
    private String name;

    @NotBlank(message = "La descripcion del articulo no puede estar vacia")
    @Size(max = 200, message = "Solo se permiten 200 caracteres")
    private String description;

    @NotNull(message = "La categoria no puede estar vacìa")
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryModel category;

    @NotNull(message = "El precio del producto no puede estar vacìo")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio del articulo debe ser igual o mayor que cero")
    private BigDecimal price;

    @NotBlank(message = "El proveedor no puede estar vacìo")
    private String provider;
}
