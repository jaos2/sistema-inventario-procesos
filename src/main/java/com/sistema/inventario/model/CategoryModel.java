package com.sistema.inventario.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "category")
@Data
public class CategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;
    @Column(unique = true)
    @NotBlank(message = "El nombre de la categoria no puede estar vacìo")
    @Size(max = 30, min = 3, message = "La categoria debe tener de 3 a 30 caracteres")
    private String nameCategory;
    @NotBlank(message = "La descripcion no puede estar vacìa")
    @Size(max = 200, message = "La descripciòn debe tener un maximo de 200 caracteres")
    private String description;

}

