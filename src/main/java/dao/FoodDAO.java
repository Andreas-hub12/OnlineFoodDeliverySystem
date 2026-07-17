package com.example.onlinefooddeliverysystem.dao;

import com.example.onlinefooddeliverysystem.database.DatabaseConnection;
import com.example.onlinefooddeliverysystem.model.FoodItem;

import java.sql.*;
import java.util.ArrayList;

public class FoodDAO {

    public ArrayList<FoodItem> getAllFood() {

        ArrayList<FoodItem> list = new ArrayList<>();

        try {

            Connection conn = DatabaseConnection.getConnection();

            String sql = "SELECT * FROM food_items";

            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                FoodItem item = new FoodItem(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("category")
                );

                list.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void addFood(String name, double price, String category) {

        try {

            Connection conn = DatabaseConnection.getConnection();

            String sql = "INSERT INTO food_items(name,price,category) VALUES(?,?,?)";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, name);
            pst.setDouble(2, price);
            pst.setString(3, category);

            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateFood(int id, String name, double price, String category) {

        try {

            Connection conn = DatabaseConnection.getConnection();

            String sql = "UPDATE food_items SET name=?, price=?, category=? WHERE id=?";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, name);
            pst.setDouble(2, price);
            pst.setString(3, category);
            pst.setInt(4, id);

            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteFood(int id) {

        try {

            Connection conn = DatabaseConnection.getConnection();

            String sql = "DELETE FROM food_items WHERE id=?";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, id);

            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}