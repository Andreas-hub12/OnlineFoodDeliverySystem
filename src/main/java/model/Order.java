package com.example.onlinefooddeliverysystem.model;

import java.util.List;

public class Order {
    private int id;
    private String customerName;
    private List<FoodItem> items;
    private double totalPrice;
    private String status;

    public Order() {}

    public Order(int id, String customerName, List<FoodItem> items, double totalPrice, String status) {
        this.id = id;
        this.customerName = customerName;
        this.items = items;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public List<FoodItem> getItems() { return items; }
    public void setItems(List<FoodItem> items) { this.items = items; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}