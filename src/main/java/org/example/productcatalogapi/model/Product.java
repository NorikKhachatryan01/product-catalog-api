package org.example.productcatalogapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDateTime;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Name can not be empty")
    private String name;

    @Column(nullable = false, unique = true)
    private String sku;
    @Column(nullable = false)
    @Min(0)
    private Double price;
    @Column(nullable = false)
    @ManyToOne
    private Category category;
    private String description;
    private LocalDateTime createdAt;


}
