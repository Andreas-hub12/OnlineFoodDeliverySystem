package com.example.onlinefooddeliverysystem.util;

import com.example.onlinefooddeliverysystem.model.FoodItem;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static final List<FoodItem> cartItems = new ArrayList<>();

    public static void addItem(FoodItem item) {
        cartItems.add(item);
    }

    public static List<FoodItem> getCartItems() {
        return cartItems;
    }

    public static void clearCart() {
        cartItems.clear();
    }

    public static double getTotal() {
        return cartItems.stream().mapToDouble(FoodItem::getPrice).sum();
    }
}