package com.example.onlinefooddeliverysystem.dao;

import com.example.onlinefooddeliverysystem.database.DatabaseConnection;
import com.example.onlinefooddeliverysystem.model.FoodItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class FoodDAO {


    public List<String> getAllRestaurants() {

        Set<String> restaurants = new LinkedHashSet<>();


        restaurants.add("Jollibee");
        restaurants.add("McDonald's");
        restaurants.add("KFC");
        restaurants.add("Chowking");
        restaurants.add("Mang Inasal");
        restaurants.add("Leylam");

        String query = "SELECT DISTINCT restaurant_name FROM food_items WHERE restaurant_name IS NOT NULL AND restaurant_name != ''";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String dbRestaurant = rs.getString("restaurant_name");
                if (dbRestaurant != null && !dbRestaurant.trim().isEmpty()) {
                    restaurants.add(dbRestaurant.trim());
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching restaurants: " + e.getMessage());
        }

        return new ArrayList<>(restaurants);
    }


    public List<FoodItem> getFoodByRestaurant(String restaurantName) {
        List<FoodItem> items = new ArrayList<>();
        String query = "SELECT * FROM food_items WHERE LOWER(restaurant_name) = LOWER(?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, restaurantName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                FoodItem item = new FoodItem(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("category"),
                        rs.getString("restaurant_name")
                );
                items.add(item);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching food by restaurant: " + e.getMessage());
        }

        return items;
    }


    public List<FoodItem> getAllFood() {
        List<FoodItem> items = new ArrayList<>();
        String query = "SELECT * FROM food_items";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                FoodItem item = new FoodItem(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("category"),
                        rs.getString("restaurant_name")
                );
                items.add(item);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all food items: " + e.getMessage());
        }

        return items;
    }


    public boolean addFood(String name, double price, String category) {
        return addFood(name, price, category, "Jollibee");
    }


    public boolean addFood(String name, double price, String category, String restaurantName) {
        String query = "INSERT INTO food_items (name, price, category, restaurant_name) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setString(3, category);
            pstmt.setString(4, restaurantName != null && !restaurantName.trim().isEmpty() ? restaurantName.trim() : "Jollibee");

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("Error adding food item: " + e.getMessage());
            return false;
        }
    }


    public boolean updateFood(int id, String name, double price, String category) {
        return updateFood(id, name, price, category, "Jollibee");
    }


    public boolean updateFood(int id, String name, double price, String category, String restaurantName) {
        return updateFood(new FoodItem(id, name, price, category, restaurantName));
    }


    public boolean updateFood(FoodItem item) {
        String query = "UPDATE food_items SET name = ?, price = ?, category = ?, restaurant_name = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, item.getName());
            pstmt.setDouble(2, item.getPrice());
            pstmt.setString(3, item.getCategory());
            pstmt.setString(4, item.getRestaurantName() != null ? item.getRestaurantName() : "Jollibee");
            pstmt.setInt(5, item.getId());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.err.println("Error updating food item: " + e.getMessage());
            return false;
        }
    }


    public boolean deleteFood(int id) {
        String query = "DELETE FROM food_items WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting food item: " + e.getMessage());
            return false;
        }
    }
}