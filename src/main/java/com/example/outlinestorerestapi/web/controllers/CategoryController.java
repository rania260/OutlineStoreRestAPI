package com.example.outlinestorerestapi.web.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.outlinestorerestapi.business.services.CategorieService;
import com.example.outlinestorerestapi.dao.entities.Category;
import com.example.outlinestorerestapi.web.models.requests.CategoryForm;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategorieService categorieService;

    // Retrieve all products
    @GetMapping
    public ResponseEntity<Object> getAllCategories() {
        return new ResponseEntity<>(this.categorieService.getAllCategory(), HttpStatus.OK);
    }

    // Retrieve a specific product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCategoryById(@PathVariable Long id) {
        Optional<Category> categorie = categorieService.getCategory(id);
        if (categorie.isPresent()) {
            return new ResponseEntity<>(categorie.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed: Product not found", HttpStatus.NOT_FOUND);
    }


// Create a new product
@PostMapping
public ResponseEntity<Object> createCategory(@RequestBody CategoryForm categoryForm) {
    Category category = new Category(null, categoryForm.getName());
    try{ this.categorieService.addCategory(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }catch(Exception e){
           return new ResponseEntity<Object>("Failed to create new product: Duplicate entry for code",HttpStatus.BAD_REQUEST);
    }
    
}
    // Update an existing product by ID
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCategoryById(@PathVariable Long id, @RequestBody CategoryForm categoryForm) {
        Optional<Category> cat = categorieService.getCategory(id);
        if (cat.isPresent()) {
            cat.get().setName(categoryForm.getName());
            this.categorieService.updateCategory(cat.get());
            return new ResponseEntity<>(cat, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategoryById(@PathVariable Long id) {
        Optional<Category> cat = categorieService.getCategory(id);
        if (cat.isPresent()) {
           this.categorieService.deleteCategory(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }   
    
    
}