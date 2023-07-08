package com.example.uasmobileno2.objects;

public class Products {
    private String productName;
    private int productPrice;
    private int amount;

    public Products(String name, int price, int amount) {
        this.productName = name;
        this.productPrice = price;
        this.amount = amount;
    }

    public void setProductName(String name) {
        this.productName = name;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductPrice(int price) {
        this.productPrice = price;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

}
