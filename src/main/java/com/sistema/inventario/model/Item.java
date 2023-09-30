package com.sistema.inventario.model;

import lombok.Data;

@Data
public class Item {
    private int id;
    private String name;
    private String description;
    private String category;
    private double price;
    private  int stock;
    private String provider;
}
