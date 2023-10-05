package com.sistema.inventario.controller;

import com.sistema.inventario.model.Item;
import com.sistema.inventario.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;


    @PostMapping("item")
    public ResponseEntity<Item> create (@RequestBody Item item){
        return new ResponseEntity<>(itemService.createItem(item), HttpStatus.CREATED);
    }
    @GetMapping("item/{id}")


    public ResponseEntity<Item> getItemById(@PathVariable Long id){

        return ResponseEntity.ok(itemService.getItemById(id));
    }

    @PutMapping("item/{id}")
    public ResponseEntity<Item> update(@RequestBody Item item,@PathVariable Long id){
        return ResponseEntity.ok(itemService.updateItem(item, id));
    }


    @DeleteMapping("item/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){

        return new ResponseEntity("se elimino el articulo", HttpStatus.NO_CONTENT);
    }


    @GetMapping("item")
    public ResponseEntity<List<Item>> getAll(){
        return ResponseEntity.ok(itemService.findAllItem());
    }

}




