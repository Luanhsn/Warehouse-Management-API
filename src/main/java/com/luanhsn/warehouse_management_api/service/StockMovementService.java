package com.luanhsn.warehouse_management_api.service;

import com.luanhsn.warehouse_management_api.dto.ProductResponse;
import com.luanhsn.warehouse_management_api.dto.StockMovementRequest;
import com.luanhsn.warehouse_management_api.dto.StockMovementResponse;
import com.luanhsn.warehouse_management_api.exception.ResourceNotFoundException;
import com.luanhsn.warehouse_management_api.model.Product;
import com.luanhsn.warehouse_management_api.model.StockMovement;
import com.luanhsn.warehouse_management_api.repository.ProductRepository;
import com.luanhsn.warehouse_management_api.repository.StockMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for stock movement business logic.
 * Handles recording and retrieving inventory movements.
 */
@Service
public class StockMovementService {

    @Autowired
    private StockMovementRepository stockMovementRepository;

    @Autowired
    private ProductRepository productRepository;

    /**
     * Returns all stock movements.
     */
    public List<StockMovementResponse> getAllMovements() {
        return stockMovementRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Returns all stock movements for a specific product.
     * Throws ResourceNotFoundException if the product does not exist.
     */
    public List<StockMovementResponse> getMovementsByProduct(Long productId) {
        productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + productId + " not found"));
        return stockMovementRepository.findByProductId(productId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Creates a new stock movement and saves it to the database.
     * Throws ResourceNotFoundException if the product does not exist.
     */
    public StockMovementResponse createMovement(StockMovementRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + request.getProductId() + " not found"));

        StockMovement movement = new StockMovement();
        movement.setProduct(product);
        movement.setQuantity(request.getQuantity());
        movement.setType(request.getType());
        movement.setNote(request.getNote());

        return toResponse(stockMovementRepository.save(movement));
    }

    /**
     * Converts a StockMovement entity to a StockMovementResponse DTO.
     */
    private StockMovementResponse toResponse(StockMovement movement) {
        StockMovementResponse response = new StockMovementResponse();
        response.setId(movement.getId());
        response.setQuantity(movement.getQuantity());
        response.setType(movement.getType());
        response.setNote(movement.getNote());
        response.setCreatedAt(movement.getCreatedAt());

        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(movement.getProduct().getId());
        productResponse.setName(movement.getProduct().getName());
        productResponse.setQuantity(movement.getProduct().getQuantity());
        productResponse.setPrice(movement.getProduct().getPrice());
        productResponse.setSku(movement.getProduct().getSku());
        response.setProduct(productResponse);

        return response;
    }
}