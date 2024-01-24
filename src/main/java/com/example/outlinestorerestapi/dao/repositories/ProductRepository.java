package com.example.outlinestorerestapi.dao.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.outlinestorerestapi.dao.entities.Product;



@Repository
public interface ProductRepository extends JpaRepository<Product, Long>  {
	Optional<Product> findByCode(String code);
}
