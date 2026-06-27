package com.luanhsn.warehouse_management_api.dto;

import com.luanhsn.warehouse_management_api.model.MovementType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class StockMovementRequest {
    private Long productId;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private MovementType type;
    private String note;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public MovementType getType() {
        return type;
    }

    public void setType(MovementType type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
