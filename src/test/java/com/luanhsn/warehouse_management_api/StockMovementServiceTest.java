package com.luanhsn.warehouse_management_api;

import com.luanhsn.warehouse_management_api.dto.ProductResponse;
import com.luanhsn.warehouse_management_api.dto.StockMovementRequest;
import com.luanhsn.warehouse_management_api.dto.StockMovementResponse;
import com.luanhsn.warehouse_management_api.model.MovementType;
import com.luanhsn.warehouse_management_api.model.Product;
import com.luanhsn.warehouse_management_api.model.StockMovement;
import com.luanhsn.warehouse_management_api.repository.ProductRepository;
import com.luanhsn.warehouse_management_api.repository.StockMovementRepository;
import com.luanhsn.warehouse_management_api.service.StockMovementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.function.Executable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for StockMovementService.
 * Uses Mockito to mock repositories — no database connection required.
 */
@ExtendWith(MockitoExtension.class)
public class StockMovementServiceTest {

    @Mock
    private StockMovementRepository stockMovementRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private StockMovementService stockMovementService;

    /**
     * Should return an empty list when no movements exist.
     */
    @Test
    void getAllMovements_shouldReturnEmptyList(){
        when(stockMovementRepository.findAll()).thenReturn(List.of());
        List<StockMovementResponse> result = stockMovementService.getAllMovements();
        assertTrue(result.isEmpty());
    }

    /**
     * Should return a movement response with correct type and quantity after creation.
     */
    @Test
    void createMovement_shouldReturnMovementWithCorrectType(){
        Product product = new Product();
        product.setName("Schraube");

        StockMovementRequest request = new StockMovementRequest();
        request.setProductId(1L);
        request.setQuantity(50);
        request.setType(MovementType.IN);
        request.setNote("Erste Lieferung");

        StockMovement saved = new StockMovement();
        saved.setProduct(product);
        saved.setQuantity(50);
        saved.setType(MovementType.IN);
        saved.setNote("Erste Lieferung");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(stockMovementRepository.save(any(StockMovement.class))).thenReturn(saved);

        StockMovementResponse response = stockMovementService.createMovement(request);

        assertEquals(MovementType.IN, response.getType());
        assertEquals(50, response.getQuantity());
        assertEquals(product.getName(), response.getProduct().getName());
    }

    /**
     * Should throw ResourceNotFoundException when product does not exist.
     */
    @Test
    void createMovement_shouldThrowException_whenProductNotFound(){
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        StockMovementRequest request = new StockMovementRequest();
        request.setProductId(99L);
        request.setQuantity(50);
        request.setType(MovementType.IN);

        assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() {
                stockMovementService.createMovement(request);
            }
        });
    }

    /**
     * Should return a non-empty list of movements for a specific product.
     */
    @Test
    void getMovementsByProduct_shouldReturnMovements(){
        Product product = new Product();
        product.setName("Schraube");
        product.setId(1L);

        StockMovement movement = new StockMovement();
        movement.setProduct(product);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(stockMovementRepository.findByProductId(1L)).thenReturn(List.of(movement));

        List<StockMovementResponse> result = stockMovementService.getMovementsByProduct(1L);

        assertFalse(result.isEmpty());
    }
}
