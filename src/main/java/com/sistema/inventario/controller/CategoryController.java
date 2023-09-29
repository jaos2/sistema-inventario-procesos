package com.sistema.inventario.controller;
import org.springframework.http.HttpStatus;
import com.sistema.inventario.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sistema.inventario.service.CategoryService;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("Category")
    public ResponseEntity<Category> create(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.CREATED);
    }

    @GetMapping("category/{id}")


    public ResponseEntity<Category> getCategoryById(@PathVariable Long id){

        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PutMapping("category/{id}")
    public ResponseEntity<Category> update(@RequestBody Category category,@PathVariable Long id){
        return ResponseEntity.ok(categoryService.updateCategory(category,id));
    }


    @DeleteMapping("category/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){

        return new ResponseEntity("Se elimino la categoria", HttpStatus.NO_CONTENT);
    }


    @GetMapping("category")
    public ResponseEntity<List<Category>> getAll(){
        return ResponseEntity.ok(categoryService.findAllCategory());
    }


}
