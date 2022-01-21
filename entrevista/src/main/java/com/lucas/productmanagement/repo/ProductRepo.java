package com.lucas.productmanagement.repo;

import com.lucas.productmanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    void deleteProductById(Long id);
    Optional<Product> findProductById(Long id);
}
