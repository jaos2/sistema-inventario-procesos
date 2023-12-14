package com.sistema.inventario.repository;

import com.sistema.inventario.model.CategoryModel;
import com.sistema.inventario.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemModel, Long> {
    Optional<ItemModel> findByName(String name);
    List<ItemModel> findByCategory(CategoryModel category);
}