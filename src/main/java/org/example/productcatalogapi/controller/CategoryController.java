package org.example.productcatalogapi.controller;

import org.example.productcatalogapi.model.Category;
import org.example.productcatalogapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
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
