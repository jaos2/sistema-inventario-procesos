package com.sistema.inventario.repository;

import com.sistema.inventario.model.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item,Long> {
    List<Item> findByNameAndDescription(String name, String description);
}

