package com.luanhsn.warehouse_management_api.dto;

import java.time.LocalDateTime;

public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private Integer quantity;
    private Double price;
    private String sku;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters und Setters

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Integer getQuantity() { return quantity; }
    public Double getPrice() { return price; }
    public String getSku() { return sku; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public void setPrice(Double price) { this.price = price; }
    public void setSku(String sku) { this.sku = sku; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}