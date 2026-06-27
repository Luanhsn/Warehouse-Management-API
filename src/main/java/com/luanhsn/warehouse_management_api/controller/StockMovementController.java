package com.luanhsn.warehouse_management_api.controller;

import com.luanhsn.warehouse_management_api.dto.StockMovementRequest;
import com.luanhsn.warehouse_management_api.dto.StockMovementResponse;
import com.luanhsn.warehouse_management_api.service.StockMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for stock movement endpoints.
 * Handles recording and retrieving inventory movements.
 */
@RestController
@RequestMapping("/api/stock-movements")
public class StockMovementController {

    @Autowired
    private StockMovementService stockMovementService;

    /**
     * Returns all stock movements.
     */
    @GetMapping
    public List<StockMovementResponse> getAllMovements() {
        return stockMovementService.getAllMovements();
    }

    /**
     * Returns all stock movements for a specific product.
     */
    @GetMapping("/product/{productId}")
    public List<StockMovementResponse> getMovementsByProduct(@PathVariable Long productId) {
        return stockMovementService.getMovementsByProduct(productId);
    }

    /**
     * Creates a new stock movement.
     */
    @PostMapping
    public StockMovementResponse createMovement(@RequestBody StockMovementRequest request) {
        return stockMovementService.createMovement(request);
    }
}