package org.example.productcatalogapi.controller;

import org.example.productcatalogapi.dto.PaginatedResponse;
import org.example.productcatalogapi.model.Category;
import org.example.productcatalogapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<PaginatedResponse<Category>> getAllCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Page must be non-negative and size must be positive.");
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categoryPage = categoryService.getAllCategories(pageable);
        PaginatedResponse.PaginationMetadata metadata = new PaginatedResponse.PaginationMetadata(
                categoryPage.getTotalElements(),
                categoryPage.getNumber(),
                categoryPage.getSize(),
                categoryPage.getTotalPages()
        );
        PaginatedResponse<Category> response = new PaginatedResponse<>(
                categoryPage.getContent(),
                metadata
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/category")
    public void createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
    }

    @GetMapping("/category")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/category/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/category")
    public void updateCategory( @RequestBody Category category) {
        Category oldCategory = categoryService.getCategoryById(category.getId());
        if(category.getName()!=null) {
            oldCategory.setName(category.getName());
        }
        categoryService.updateCategory(oldCategory);
    }

    @DeleteMapping("/category/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
    }
}
