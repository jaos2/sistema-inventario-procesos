package com.sistema.inventario.service;

import com.sistema.inventario.exceptions.AlreadyExistsException;
import com.sistema.inventario.exceptions.NotFoundException;
import com.sistema.inventario.model.CategoryModel;
import com.sistema.inventario.repository.CategoryRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepositories categoryRepositories;

    public CategoryModel createItem(CategoryModel category){
        if (categoryRepositories.findByNameCategory(category.getNameCategory()).isPresent()) {
            throw new AlreadyExistsException("La categoria con el nombre" + category.getNameCategory() + " ya existe");
        }
        return categoryRepositories.save(category);
    }

    public CategoryModel getItemByid(Long id){
        Optional<CategoryModel> category = categoryRepositories.findById(id);
        if(category.isEmpty()){
            throw new NotFoundException("Categoria no encontrada");
        }
        return category.get();
    }

    public CategoryModel updateItem(CategoryModel category, Long id){
        if(!categoryRepositories.existsById(id)){
            throw new NotFoundException("Categoria no encontrada");
        }
        Optional<CategoryModel> existingCategoryOptional = categoryRepositories.findByNameCategory(category.getNameCategory());
        if (existingCategoryOptional.isPresent() && !existingCategoryOptional.get().getCategory_id().equals(id)) {
            throw new AlreadyExistsException("La categoria con el nombre " + category.getNameCategory() + " ya existe");
        }
        CategoryModel categoryDB = categoryRepositories.findById(id).get();
            categoryDB.setNameCategory(category.getNameCategory());
            categoryDB.setDescription(category.getDescription());
            return categoryRepositories.save(categoryDB);

    }

    public boolean deleteCategoryById(Long id){
        if(categoryRepositories.existsById(id)){
            categoryRepositories.deleteById(id);
            return true;
        } else {
            throw new NotFoundException("Categoria no encontrada");
        }

    }

    public List<CategoryModel> findAllCategory(){
        List<CategoryModel> categories =  categoryRepositories.findAll();
        if (categories.isEmpty()) {
            throw new NotFoundException("No se encontraron categorias");
        }
        return categories;
    }
}
