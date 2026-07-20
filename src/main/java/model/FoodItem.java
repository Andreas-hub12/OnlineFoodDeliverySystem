package com.example.onlinefooddeliverysystem.model;

import java.io.Serializable;

public class FoodItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private double price;
    private String category;
    private String restaurantName;

    public FoodItem() {}

    public FoodItem(int id, String name, double price, String category, String restaurantName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.restaurantName = restaurantName;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getRestaurantName() { return restaurantName; }
    public void setRestaurantName(String restaurantName) { this.restaurantName = restaurantName; }
}