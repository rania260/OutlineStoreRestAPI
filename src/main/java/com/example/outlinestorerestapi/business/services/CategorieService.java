package com.example.outlinestorerestapi.business.services;

import java.util.List;
import java.util.Optional;

import com.example.outlinestorerestapi.dao.entities.Category;

public interface CategorieService {

    public List<Category> getAllCategory();

    public Optional<Category> getCategory(Long id);
   
    public Category addCategory(Category C);
   
    public Category updateCategory(Category C);

    public void deleteCategory(Long id);
}