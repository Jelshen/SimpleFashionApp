package com.example.uasmobileno2.objects;

public class Orders {
    private String items;
    private String price;
    private String amount;
    private String date;

    public Orders(String items, String price, String amount, String date) {
        this.items = items;
        this.price = price;
        this.amount = amount;
        this.date = date;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
    public void setDate(String date) { this.date = date; }

    public String getItems() { return this.items; }
    public String getPrice() { return this.price; }
    public String getAmount() { return this.amount; }
    public String getDate() { return this.date; }
}
