package com.example.outlinestorerestapi.business.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.outlinestorerestapi.business.services.CategorieService;
import com.example.outlinestorerestapi.dao.entities.Category;
import com.example.outlinestorerestapi.dao.repositories.CategoryRepository;

public class CategorieServiceImpl implements CategorieService{

    @Autowired
    CategoryRepository categorieRepository;

    @Override
    public List<Category> getAllCategory() {
        // TODO Auto-generated method stub
        return this.categorieRepository.findAll();
    }

    @Override
    public Optional<Category> getCategory(Long id) {
        // TODO Auto-generated method stub
        return this.categorieRepository.findById(id);
    }

    @Override
    public Category addCategory(Category C) {
        // TODO Auto-generated method stub
        return this.categorieRepository.save(C);
    }

    @Override
    public Category updateCategory(Category C) {
        // TODO Auto-generated method stub
        return this.categorieRepository.save(C);
    }

    @Override
    public void deleteCategory(Long id) {
        // TODO Auto-generated method stub
        this.categorieRepository.deleteById(id);
    }
    
}