package com.luanhsn.warehouse_management_api.controller;

import com.luanhsn.warehouse_management_api.dto.ProductRequest;
import com.luanhsn.warehouse_management_api.dto.ProductResponse;
import com.luanhsn.warehouse_management_api.model.Product;
import com.luanhsn.warehouse_management_api.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for product endpoints.
 * Handles CRUD operations for warehouse products.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Returns all products.
     */
    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Returns a single product by ID.
     * Throws ResourceNotFoundException if the product does not exist.
     */
    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    /**
     * Creates a new product and saves it to the database.
     */
    @PostMapping
    public ProductResponse createProduct(@Valid @RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }

    /**
     * Deletes a product by ID.
     */
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    /**
     * Updates an existing product by ID.
     */
    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
        return productService.updateProduct(id, request);
    }
}