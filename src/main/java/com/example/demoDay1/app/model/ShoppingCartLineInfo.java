package com.example.demoDay1.app.model;

import lombok.Data;

@Data
public class ShoppingCartLineInfo {

    private BookInfo bookInfo;
    private int quantity;

    public ShoppingCartLineInfo() {
        this.setQuantity(0);
    }

    public double getSum() {
        return this.quantity * this.getBookInfo().getPrice();
    }
}
