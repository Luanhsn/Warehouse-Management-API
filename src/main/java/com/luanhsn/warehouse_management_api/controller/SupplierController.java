package com.luanhsn.warehouse_management_api.controller;

import com.luanhsn.warehouse_management_api.dto.SupplierRequest;
import com.luanhsn.warehouse_management_api.dto.SupplierResponse;
import com.luanhsn.warehouse_management_api.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for supplier endpoints.
 * Handles CRUD operations for suppliers.
 */
@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    /**
     * Returns all suppliers.
     */
    @GetMapping
    public List<SupplierResponse> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    /**
     * Returns a single supplier by ID.
     * Throws ResourceNotFoundException if the supplier does not exist.
     */
    @GetMapping("/{id}")
    public SupplierResponse getSupplierById(@PathVariable Long id) {
        return supplierService.getSupplierById(id);
    }

    /**
     * Creates a new supplier and saves it to the database.
     */
    @PostMapping
    public SupplierResponse createSupplier(@Valid @RequestBody SupplierRequest request) {
        return supplierService.createSupplier(request);
    }

    /**
     * Updates an existing supplier by ID.
     */
    @PutMapping("/{id}")
    public SupplierResponse updateSupplier(@PathVariable Long id, @Valid @RequestBody SupplierRequest request) {
        return supplierService.updateSupplier(id, request);
    }

    /**
     * Deletes a supplier by ID.
     */
    @DeleteMapping("/{id}")
    public void deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
    }
}