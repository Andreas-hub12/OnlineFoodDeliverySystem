package com.example.onlinefooddeliverysystem.util;

public class AppState {
    private static String selectedRestaurant = "Jollibee";

    public static String getSelectedRestaurant() {
        return selectedRestaurant;
    }

    public static void setSelectedRestaurant(String restaurant) {
        selectedRestaurant = restaurant;
    }
}