package com.sistema.inventario.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;


@Entity
@Data
@Table(name = "category")
public class CategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;
    @Column(unique = true)
    @NotBlank(message = "Category name cannot be empty")
    @Size(max = 50, min = 3, message = "Category name must be between 3 and 50 characters")
    private String nameCategory;
    @NotBlank(message = "Description cannot be empty")
    @Size(max = 300, message = "Maximum 300 characters in description")
    private String description;
    @NotBlank(message = "Status cannot be empty")
    private String status;

}
