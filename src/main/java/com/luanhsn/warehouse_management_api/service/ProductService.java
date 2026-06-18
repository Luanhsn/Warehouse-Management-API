package com.luanhsn.warehouse_management_api.service;

import com.luanhsn.warehouse_management_api.dto.ProductRequest;
import com.luanhsn.warehouse_management_api.dto.ProductResponse;
import com.luanhsn.warehouse_management_api.exception.ResourceNotFoundException;
import com.luanhsn.warehouse_management_api.model.Product;
import com.luanhsn.warehouse_management_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for product business logic.
 * Handles conversion between model and DTOs, and database operations.
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Updates an existing product by ID.
     * Throws ResourceNotFoundException if the product does not exist.
     */
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
        existing.setName(request.getName());
        existing.setDescription(request.getDescription());
        existing.setQuantity(request.getQuantity());
        existing.setPrice(request.getPrice());
        existing.setSku(request.getSku());
        return toResponse(productRepository.save(existing));
    }

    /**
     * Converts a ProductRequest to a Product entity.
     */
    private Product toEntity(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setQuantity(request.getQuantity());
        product.setPrice(request.getPrice());
        product.setSku(request.getSku());
        return product;
    }

    /**
     * Converts a Product entity to a ProductResponse DTO.
     */
    private ProductResponse toResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setQuantity(product.getQuantity());
        response.setPrice(product.getPrice());
        response.setSku(product.getSku());
        response.setCreatedAt(product.getCreatedAt());
        response.setUpdatedAt(product.getUpdatedAt());
        return response;
    }

    /**
     * Returns all products from the database.
     */
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> toResponse(product))
                .collect(Collectors.toList());
    }

    /**
     * Returns a single product by ID.
     * Throws ResourceNotFoundException if the product does not exist.
     */
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
        return toResponse(product);
    }

    /**
     * Creates a new product and saves it to the database.
     */
    public ProductResponse createProduct(ProductRequest request) {
        Product product = toEntity(request);
        return toResponse(productRepository.save(product));
    }

    /**
     * Deletes a product by ID.
     */
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}