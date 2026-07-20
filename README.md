# Online Food Delivery System

## Software System Description
This project is a comprehensive desktop-based food delivery management system designed to streamline the ordering process between restaurants and customers. It features role-based access, inventory management, and real-time order tracking.

## Major Features
* **Admin Dashboard:** CRUD operations for food menu management.
* **Customer Interface:** Restaurant browsing, search, and ordering system.
* **Persistent Cart:** A centralized shopping cart that maintains state across various UI views.
* **Order Tracking:** Real-time status updates from preparation to delivery.
* **Secure Session Management:** Maintains user state across the application using Java Serialization.

## Serialization Mechanism (Session Management)
To maintain user state, the system implements Java Serialization.
* **Implementation:** Upon a successful login, the application captures the authenticated user's data into a `UserSession` object. This object is serialized and saved to a local file named `session.dat` using `ObjectOutputStream`.
* **Usage:** Throughout the application lifecycle, the system checks for the presence of `session.dat` to validate the active user's permissions.
* **Logout:** Upon logging out, the `SessionManager` explicitly deletes the `session.dat` file using the `java.io.File.delete()` method, ensuring secure session termination and preventing unauthorized access.

## SOLID Design Principles Applied

### 1. Single Responsibility Principle (SRP)
* **Classes Involved:** `FoodDAO`, `DatabaseConnection`, `MenuController`.
* **Application:** I separated database connection logic (in `DatabaseConnection`), data persistence operations (in `FoodDAO`), and UI display logic (in `MenuController`).
* **Benefit:** This separation ensures that each class has one reason to change. If the database schema changes, only the `DAO` requires updates; the UI remains untouched, significantly reducing the risk of bugs during maintenance.

### 2. Open/Closed Principle (OCP)
* **Classes Involved:** `NavigationManager` and individual Controllers.
* **Application:** The system is designed to be extendable. When adding new features (like the new Checkout, Tracking, or Review screens), I did not need to modify the core application logic. I simply "extended" the system by creating new FXML/Controller pairs and registering the routes in the `NavigationManager`.
* **Benefit:** Existing, tested code remains "closed" to modification, which minimizes the risk of introducing regression errors while keeping the system "open" for new functionality.

### 3. Dependency Inversion Principle (DIP)
* **Classes Involved:** `MenuController` and `FoodDAO`.
* **Application:** High-level modules (Controllers) do not directly manipulate low-level database execution details. Instead, the Controllers rely on `DAO` classes to provide data.
* **Benefit:** This decouples the business logic from specific database implementation details. It allows for easier unit testing of the controllers, as the data layer can be swapped without requiring changes to the user interface code.

---
## Tech Stack & Environment
* **Java 21:** Business Logic
* **JavaFX, FXML & CSS:** Frontend UI Design
* **SceneBuilder:** UI Layout Tool
* **MySQL (via XAMPP):** Database Management
* **IntelliJ IDEA:** Integrated Development Environment (IDE)
* **Git:** Version Control System