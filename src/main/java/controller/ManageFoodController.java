package com.example.onlinefooddeliverysystem.controller;

import com.example.onlinefooddeliverysystem.dao.FoodDAO;
import com.example.onlinefooddeliverysystem.model.FoodItem;
import com.example.onlinefooddeliverysystem.util.NavigationManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableColumn<FoodItem, String> colRestaurant;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtCategory;

    @FXML
    private ComboBox<String> cmbRestaurant; // Dropdown control

    private final FoodDAO foodDAO = new FoodDAO();

    @FXML
    public void initialize() {
        // Map columns
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colRestaurant.setCellValueFactory(new PropertyValueFactory<>("restaurantName"));

        // Format price
        colPrice.setCellFactory(tc -> new TableCell<FoodItem, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty || price == null) {
                    setText(null);
                } else {
                    setText(String.format("$%.2f", price));
                }
            }
        });

        populateRestaurantDropdown();


        tableFood.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, selectedItem) -> {
            if (selectedItem != null) {
                txtName.setText(selectedItem.getName());
                txtPrice.setText(String.valueOf(selectedItem.getPrice()));
                txtCategory.setText(selectedItem.getCategory());
                cmbRestaurant.setValue(selectedItem.getRestaurantName());
            }
        });

        loadTable();
    }

    private void populateRestaurantDropdown() {
        cmbRestaurant.setItems(FXCollections.observableArrayList(foodDAO.getAllRestaurants()));
        cmbRestaurant.setEditable(true); // Allows typing a custom restaurant name if not in list
    }

    @FXML
    private void handleAdd(ActionEvent event) {
        if (!validateInputs()) return;

        try {
            String name = txtName.getText().trim();
            double price = Double.parseDouble(txtPrice.getText().trim());
            String category = txtCategory.getText().trim();
            String restaurant = getSelectedRestaurant();

            if (foodDAO.addFood(name, price, category, restaurant)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Food item added successfully!");
                clearFields();
                populateRestaurantDropdown(); // Refresh dropdown
                loadTable();
            } else {
                showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to add food item.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Price must be a valid number.");
        }
    }

    @FXML
    private void handleUpdate(ActionEvent event) {
        FoodItem selected = tableFood.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select an item from the table to update.");
            return;
        }

        if (!validateInputs()) return;

        try {
            selected.setName(txtName.getText().trim());
            selected.setPrice(Double.parseDouble(txtPrice.getText().trim()));
            selected.setCategory(txtCategory.getText().trim());
            selected.setRestaurantName(getSelectedRestaurant());

            if (foodDAO.updateFood(selected)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Food item updated successfully!");
                clearFields();
                populateRestaurantDropdown();
                loadTable();
            } else {
                showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to update food item.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Price must be a valid number.");
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        FoodItem selected = tableFood.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select an item from the table to delete.");
            return;
        }

        if (foodDAO.deleteFood(selected.getId())) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Food item deleted successfully!");
            clearFields();
            populateRestaurantDropdown();
            loadTable();
        } else {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to delete food item.");
        }
    }

    @FXML
    private void handleRefresh(ActionEvent event) {
        clearFields();
        populateRestaurantDropdown();
        loadTable();
    }

    @FXML
    private void handleBack(ActionEvent event) {
        NavigationManager.navigateTo(event, "admin.fxml");
    }

    private void loadTable() {
        tableFood.setItems(FXCollections.observableArrayList(foodDAO.getAllFood()));
    }

    private String getSelectedRestaurant() {
        if (cmbRestaurant.getValue() != null) {
            return cmbRestaurant.getValue().trim();
        } else if (cmbRestaurant.getEditor() != null) {
            return cmbRestaurant.getEditor().getText().trim();
        }
        return "";
    }

    private void clearFields() {
        txtName.clear();
        txtPrice.clear();
        txtCategory.clear();
        cmbRestaurant.setValue(null);
        if (cmbRestaurant.getEditor() != null) cmbRestaurant.getEditor().clear();
        tableFood.getSelectionModel().clearSelection();
    }

    private boolean validateInputs() {
        if (txtName.getText().trim().isEmpty() ||
                txtPrice.getText().trim().isEmpty() ||
                txtCategory.getText().trim().isEmpty() ||
                getSelectedRestaurant().isEmpty()) {

            showAlert(Alert.AlertType.WARNING, "Validation Error", "Please fill in all fields (Name, Price, Category, and Restaurant).");
            return false;
        }
        return true;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}