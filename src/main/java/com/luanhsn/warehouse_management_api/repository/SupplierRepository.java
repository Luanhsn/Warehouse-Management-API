package com.luanhsn.warehouse_management_api.repository;

import com.luanhsn.warehouse_management_api.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}