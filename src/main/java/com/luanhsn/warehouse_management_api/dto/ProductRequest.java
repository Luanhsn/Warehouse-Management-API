package com.luanhsn.warehouse_management_api.dto;

public class ProductRequest {
    private String name;
    private String description;
    private Integer quantity;
    private Double price;
    private String sku;

    // Getters und Setters

    public String getName() { return name; }
    public String getDescription() { return description; }
    public Integer getQuantity() { return quantity; }
    public Double getPrice() { return price; }
    public String getSku() { return sku; }

    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public void setPrice(Double price) { this.price = price; }
    public void setSku(String sku) { this.sku = sku; }
}