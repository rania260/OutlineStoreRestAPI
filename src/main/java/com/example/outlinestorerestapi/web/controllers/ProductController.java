package com.example.outlinestorerestapi.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.outlinestorerestapi.business.services.IProductService;
import com.example.outlinestorerestapi.dao.entities.Product;
import com.example.outlinestorerestapi.web.models.requests.ProductForm;

import java.util.Collections;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    IProductService productService;

    // Create a new product
    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody ProductForm productForm) {
        Product product = new Product(null, productForm.getCode(), productForm.getName(), productForm.getPrice(),
        productForm.getQuantity(), productForm.getImage());
        try{ this.productService.addProduct(product);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        }catch(Exception e){
               return new ResponseEntity<Object>("Failed to create new product: Duplicate entry for code",HttpStatus.BAD_REQUEST);
        }
        
    }

    // Retrieve all products
    @GetMapping
    public ResponseEntity<Object> getAllProducts() {
        return new ResponseEntity<>(this.productService.getAllProduct(), HttpStatus.OK);
    }

    // Retrieve a specific product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProduct(id);
        if (product.isPresent()) {
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed: Product not found", HttpStatus.NOT_FOUND);
    }

    // Retrieve a specific product by code
    @GetMapping("/code/{code}")
    public ResponseEntity<Object> getProductByCode(@PathVariable String code) {
        Optional<Product> product = productService.getProductByCode(code);
        if (product.isPresent()) {
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed: Product not found", HttpStatus.NOT_FOUND);
    }

    // Retrieve all products sorted by price in ascending order
    
    @GetMapping("/sorted")
    public ResponseEntity<Object> getAllProductsSortedByPrice() {
        List<Product> sortedProducts = sortProductsByPrice(this.productService.getAllProduct());
        return new ResponseEntity<>(sortedProducts, HttpStatus.OK);
    }

    // Update an existing product by ID
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProductById(@PathVariable Long id, @RequestBody ProductForm productForm) {
        Optional<Product> product = productService.getProduct(id);
        if (product.isPresent()) {
            product.get().setCode(productForm.getCode());
            product.get().setName(productForm.getName());
            product.get().setPrice(productForm.getPrice());
            product.get().setQuantity(productForm.getQuantity());
            product.get().setImage(productForm.getImage());
            this.productService.updatePorduct(product.get());
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProduct(id);
        if (product.isPresent()) {
           this.productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }   
    // Method to sort products by price in ascending order
    private List<Product> sortProductsByPrice(List<Product> productList) {
        Collections.sort(productList, (p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
        return productList;
    }
}