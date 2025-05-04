package org.example.productcatalogapi.controller;

import org.example.productcatalogapi.model.Product;
import org.example.productcatalogapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/product")
    public Product addProduct(@RequestBody Product product) {
      return   productService.createProduct(product);
    }

    @PutMapping("/product/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody Product product) {
        productService.updateProduct(id,product);
    }

    @DeleteMapping
    public void deleteProduct(@RequestBody Product product) {
        productService.deleteProductById(product.getId());
    }
}
