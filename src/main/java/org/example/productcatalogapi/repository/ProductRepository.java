package org.example.productcatalogapi.repository;

import jakarta.validation.constraints.NotBlank;
import org.example.productcatalogapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findBySku(String sku);
    List<Product> findByCategoryId(Long categoryId);

    boolean existsBySku(@NotBlank(message = "SKU cannot be empty") String sku);
}
