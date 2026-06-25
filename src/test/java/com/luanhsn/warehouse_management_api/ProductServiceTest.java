package com.luanhsn.warehouse_management_api;

import com.luanhsn.warehouse_management_api.dto.ProductRequest;
import com.luanhsn.warehouse_management_api.dto.ProductResponse;
import com.luanhsn.warehouse_management_api.exception.ResourceNotFoundException;
import com.luanhsn.warehouse_management_api.model.Product;
import com.luanhsn.warehouse_management_api.repository.ProductRepository;
import com.luanhsn.warehouse_management_api.repository.SupplierRepository;
import com.luanhsn.warehouse_management_api.service.ProductService;
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
 * Unit tests for ProductService.
 * Uses Mockito to mock repositories — no database connection required.
 */
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private ProductService productService;

    /**
     * Should return an empty list when no products exist.
     */
    @Test
    void getAllProducts_shouldReturnEmptyList() {
        when(productRepository.findAll()).thenReturn(List.of());
        List<ProductResponse> result = productService.getAllProducts();
        assertTrue(result.isEmpty());
    }

    /**
     * Should return a product response with correct fields after creation.
     */
    @Test
    void createProduct_shouldReturnProductWithCorrectName() {
        ProductRequest request = new ProductRequest();
        request.setName("Schraube");
        request.setQuantity(100);
        request.setPrice(0.50);

        Product saved = new Product();
        saved.setName("Schraube");
        saved.setQuantity(100);
        saved.setPrice(0.50);

        when(productRepository.save(any(Product.class))).thenReturn(saved);

        ProductResponse response = productService.createProduct(request);

        assertEquals("Schraube", response.getName());
        assertEquals(100, response.getQuantity());
        assertEquals(0.50, response.getPrice());
    }

    /**
     * Should return a product when it exists in the repository.
     */
    @Test
    void getProductById_shouldReturnProduct(){
        Product product = new Product();
        product.setName("Schraube");
        product.setQuantity(100);
        product.setPrice(0.50);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        ProductResponse response = productService.getProductById(1L);

        assertEquals("Schraube", response.getName());
        assertEquals(100, response.getQuantity());
    }

    /**
     * Should throw ResourceNotFoundException when product does not exist.
     */
    @Test
    void getProductById_shouldThrowException(){
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() {
                productService.getProductById(99L);
            }
        });
    }

    /**
     * Should call deleteById on the repository when deleting a product.
     */
    @Test
    void deleteProduct_shouldCallRepository() {
        productService.deleteProduct(1L);

        verify(productRepository).deleteById(1L);
    }

    /**
     * Should return updated product response with new field values.
     */
    @Test
    void updateProduct_shouldUpdateFields() {
        // Arrange
        Product existing = new Product();
        existing.setName("Alt");
        existing.setQuantity(10);
        existing.setPrice(1.00);

        ProductRequest request = new ProductRequest();
        request.setName("Neu");
        request.setQuantity(50);
        request.setPrice(2.00);

        Product updated = new Product();
        updated.setName("Neu");
        updated.setQuantity(50);
        updated.setPrice(2.00);

        when(productRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(productRepository.save(any(Product.class))).thenReturn(updated);

        // Act
        ProductResponse response = productService.updateProduct(1L, request);

        // Assert
        assertEquals("Neu", response.getName());
        assertEquals(50, response.getQuantity());
        assertEquals(2.00, response.getPrice());
    }
}