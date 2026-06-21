package com.luanhsn.warehouse_management_api.service;

import com.luanhsn.warehouse_management_api.dto.SupplierRequest;
import com.luanhsn.warehouse_management_api.dto.SupplierResponse;
import com.luanhsn.warehouse_management_api.exception.ResourceNotFoundException;
import com.luanhsn.warehouse_management_api.model.Supplier;
import com.luanhsn.warehouse_management_api.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for supplier business logic.
 * Handles conversion between model and DTOs, and database operations.
 */
@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    /**
     * Returns all suppliers from the database.
     */
    public List<SupplierResponse> getAllSuppliers() {
        return supplierRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Returns a single supplier by ID.
     * Throws ResourceNotFoundException if the supplier does not exist.
     */
    public SupplierResponse getSupplierById(Long id) {
        return toResponse(supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier with id " + id + " not found")));
    }

    /**
     * Creates a new supplier and saves it to the database.
     */
    public SupplierResponse createSupplier(SupplierRequest request) {
        return toResponse(supplierRepository.save(toEntity(request)));
    }

    /**
     * Updates an existing supplier by ID.
     * Throws ResourceNotFoundException if the supplier does not exist.
     */
    public SupplierResponse updateSupplier(Long id, SupplierRequest request) {
        Supplier existing = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier with id " + id + " not found"));
        existing.setName(request.getName());
        existing.setEmail(request.getEmail());
        existing.setPhone(request.getPhone());
        existing.setAddress(request.getAddress());
        return toResponse(supplierRepository.save(existing));
    }

    /**
     * Deletes a supplier by ID.
     */
    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }

    /**
     * Converts a SupplierRequest to a Supplier entity.
     */
    private Supplier toEntity(SupplierRequest request) {
        Supplier supplier = new Supplier();
        supplier.setName(request.getName());
        supplier.setEmail(request.getEmail());
        supplier.setPhone(request.getPhone());
        supplier.setAddress(request.getAddress());
        return supplier;
    }

    /**
     * Converts a Supplier entity to a SupplierResponse DTO.
     */
    private SupplierResponse toResponse(Supplier supplier) {
        SupplierResponse response = new SupplierResponse();
        response.setId(supplier.getId());
        response.setName(supplier.getName());
        response.setEmail(supplier.getEmail());
        response.setPhone(supplier.getPhone());
        response.setAddress(supplier.getAddress());
        response.setCreatedAt(supplier.getCreatedAt());
        return response;
    }
}