package org.example.productcatalogapi.repository;

import jakarta.validation.constraints.NotBlank;
import org.example.productcatalogapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;



@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByName(@NotBlank(message = "Category name cannot be empty") String name);
}
