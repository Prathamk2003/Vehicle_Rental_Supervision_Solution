package Vehicle_Rental_Supervision_Solution_Project.Vehicle_Rental_Supervision_Solution_Project;

import java.sql.*;

public class DatabaseManager {
    private Connection connection;

    public DatabaseManager() {
        try {
            // Replace with your database URL, username, and password
            String url = "jdbc:mysql://localhost:3306/testing";
            String username = "root";
            String password = "Shreyas";

            // Initialize your database connection here
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.err.println("Error establishing database connection: " + e.getMessage());
        }
    }

    public ResultSet getAllVehicles() throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT * FROM vehicles");
    }

    public boolean isVehicleAvailable(String vehicleID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT status_aval FROM vehicles WHERE id = ?");
        statement.setString(1, vehicleID);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString("status_aval").equals("available");
        }
        return false;
    }

    public boolean bookVehicleForCustomer(String vehicleID, Customer customer, String startDateStr, String endDateStr) {
        try {
            // Check if the vehicle is available
            if (isVehicleAvailable(vehicleID)) {
                // Start a transaction
                connection.setAutoCommit(false);

                // Insert the booking details into the 'Bookings' table
                String insertSql = "INSERT INTO Bookings (customerName, customerEmail, customerPhone, vehicleID, startDate, endDate) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
                    insertStatement.setString(1, customer.getName());
                    insertStatement.setString(2, customer.getEmail());
                    insertStatement.setString(3, customer.getPhone());
                    insertStatement.setString(4, vehicleID);
                    insertStatement.setString(5, startDateStr);
                    insertStatement.setString(6, endDateStr);
                    insertStatement.executeUpdate();
                }

                // Update the status of the vehicle in the 'Vehicles' table
                String updateSql = "UPDATE Vehicles SET status_aval = 'booked' WHERE id = ? AND status_aval = 'available'";
                try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                    updateStatement.setString(1, vehicleID);
                    int rowsUpdated = updateStatement.executeUpdate();
                    if (rowsUpdated > 0) {
                        // Commit the transaction
                        connection.commit();
                        return true;
                    } else {
                        // Rollback the transaction
                        connection.rollback();
                        return false;
                    }
                }
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error booking vehicle: " + e.getMessage());
            return false;
        } finally {
            try {
                // End the transaction
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("Error ending transaction: " + e.getMessage());
            }
        }
    }

    public void setAllVehiclesAvailable() {
        String sql = "UPDATE Vehicles SET status_aval = 'available'";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to set all vehicles as available: " + e.getMessage());
        }
    }

    public String addVehicle(Vehicle vehicle) {
        // Check if the connection is established
        if (connection == null) {
            return "Database connection is not established.";
        }

        String sql = "INSERT INTO Vehicles (id, name, type, seats,status_aval) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, vehicle.getId());
            pstmt.setString(2, vehicle.getName());
            pstmt.setString(3, vehicle.getType());
            pstmt.setInt(4, vehicle.getSeats());
            pstmt.setString(5,vehicle.get_status_aval());
            pstmt.executeUpdate();
            return "Vehicle added: " + vehicle.getName();
        } catch (SQLException e) {
            return "Failed to add vehicle: " + e.getMessage();
        }
    }

    public String returnVehicle(String vehicleID) {
        // Check if the connection is established
        if (connection == null) {
            return "Database connection is not established.";
        }

        String sql = "UPDATE Vehicles SET status = 'available' WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, vehicleID);
            pstmt.executeUpdate();
            return "Vehicle ID " + vehicleID + " has been returned and marked as available.";
        } catch (SQLException e) {
            return "Failed to return vehicle: " + e.getMessage();
        }
    }
}