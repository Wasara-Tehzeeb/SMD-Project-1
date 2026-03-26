package com.example.myapplication;

public class Snack {
    private int imageResId;
    private String name;
    private int price;
    private int quantity;

    public Snack(int imageResId, String name, int price) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
        this.quantity = 0;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public int getImageResId() { return imageResId; }

    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
