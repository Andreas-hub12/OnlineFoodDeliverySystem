package com.example.onlinefooddeliverysystem.database;

import java.sql.Connection;

public class TestConnection {

    public static void main(String[] args) {
        Connection connection = com.example.onlinefooddeliverysystem.database.DatabaseConnection.getInstance().getConnection();

        if (connection != null) {
            System.out.println("Database Connected Successfully!");
        } else {
            System.out.println("Connection Failed!");
        }
    }
}