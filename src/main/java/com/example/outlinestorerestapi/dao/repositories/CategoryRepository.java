package com.example.outlinestorerestapi.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.outlinestorerestapi.dao.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Long>{
    
}