package com.example.onlinefooddeliverysystem.controller;

import com.example.onlinefooddeliverysystem.dao.FoodDAO;
import com.example.onlinefooddeliverysystem.model.FoodItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ManageFoodController {

    @FXML
    private TableView<FoodItem> tableFood;

    @FXML
    private TableColumn<FoodItem, String> colName;

    @FXML
    private TableColumn<FoodItem, Double> colPrice;

    @FXML
    private TableColumn<FoodItem, String> colCategory;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtCategory;

    private final FoodDAO dao = new FoodDAO();

    @FXML
    public void initialize() {

        colName.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("price"));
        colCategory.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("category"));

        loadTable();

        tableFood.setOnMouseClicked(e -> {

            FoodItem item = tableFood.getSelectionModel().getSelectedItem();

            if (item != null) {

                txtName.setText(item.getName());
                txtPrice.setText(String.valueOf(item.getPrice()));
                txtCategory.setText(item.getCategory());

            }

        });

    }

    private void loadTable() {

        ObservableList<FoodItem> list = FXCollections.observableArrayList(dao.getAllFood());

        tableFood.setItems(list);

    }

    @FXML
    private void handleAdd() {

        dao.addFood(
                txtName.getText(),
                Double.parseDouble(txtPrice.getText()),
                txtCategory.getText()
        );

        loadTable();

    }

    @FXML
    private void handleDelete() {

        FoodItem item = tableFood.getSelectionModel().getSelectedItem();

        if (item != null) {

            dao.deleteFood(item.getId());

            loadTable();

        }

    }

    @FXML
    private void handleUpdate() {

        FoodItem item = tableFood.getSelectionModel().getSelectedItem();

        if (item != null) {

            dao.updateFood(
                    item.getId(),
                    txtName.getText(),
                    Double.parseDouble(txtPrice.getText()),
                    txtCategory.getText()
            );

            loadTable();

        }

    }
    @FXML
    private void handleBack(javafx.event.ActionEvent event) throws java.io.IOException {

        javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(
                getClass().getResource("/fxml/admin.fxml"));

        javafx.stage.Stage stage =
                (javafx.stage.Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();

        stage.setScene(new javafx.scene.Scene(root));
        stage.show();
    }

    @FXML
    private void handleRefresh() {

        loadTable();

    }

}