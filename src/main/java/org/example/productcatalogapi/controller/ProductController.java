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

    @PutMapping
    public void updateProduct(@RequestBody Product product) {
        Product oldProduct = productService.getProductById(product.getId());
        if(product.getName() != null) {
            oldProduct.setName(product.getName());
        }
        if(product.getDescription() != null) {
            oldProduct.setDescription(product.getDescription());
        }
        if(product.getPrice() != null) {
            oldProduct.setPrice(product.getPrice());
        }
        if(product.getCategory() != null) {
            oldProduct.setCategory(product.getCategory());
        }
        if (product.getSku() != null) {
            oldProduct.setSku(product.getSku());
        }
        productService.createProduct(oldProduct);
    }

    @DeleteMapping
    public void deleteProduct(@RequestBody Product product) {
        productService.deleteProductById(product.getId());
    }
}
