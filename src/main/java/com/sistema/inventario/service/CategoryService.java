package com.sistema.inventario.service;

import com.sistema.inventario.model.Category;
import com.sistema.inventario.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category getCategoryById(Long id){
        return categoryRepository.findById(id).get();
    }

    public Category updateCategory(Category category, long id){
        if (categoryRepository.existsById(id)){
            Category categoryBd = categoryRepository.findById(id).get();
            return categoryRepository.save(categoryBd);
        }
        return null;
    }
    public Boolean deleteCategoryById(Long id){
        if (categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public List<Category> findAllCategory(){
        return (List<Category>) categoryRepository.findAll();
    }



}
