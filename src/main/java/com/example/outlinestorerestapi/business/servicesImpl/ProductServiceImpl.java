package com.example.outlinestorerestapi.business.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.outlinestorerestapi.business.services.IProductService;
import com.example.outlinestorerestapi.dao.entities.Product;
import com.example.outlinestorerestapi.dao.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
	ProductRepository productRepository;

    @Override
    public Optional<Product> getProduct(Long id) {
       
      return   this.productRepository.findById(id);
    }
    
    @Override
    public Optional<Product> getProductByCode(String code) {
       
      return   this.productRepository.findByCode(code);
    }

    @Override
    public Product addProduct(Product product) {
      return this.productRepository.save(product);
    }

    @Override
    public Product updatePorduct(Product product) {
       return this.productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
          this.productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllProduct() {
        return this.productRepository.findAll();
    }
    
}