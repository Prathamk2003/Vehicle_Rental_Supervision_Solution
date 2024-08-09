package Vehicle_Rental_Supervision_Solution_Project.Vehicle_Rental_Supervision_Solution_Project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Vehicle_Manager {
    private final DatabaseManager dbManager = new DatabaseManager();
    private static final double RATE_PER_DAY = 100.0; // Daily rate in rupees

    public String displayAvailableVehicles() {
        StringBuilder vehicleDetails = new StringBuilder();
        try {
            ResultSet vehicles = dbManager.getAllVehicles();
            while (vehicles.next()) {
                String id = vehicles.getString("id");
                String name = vehicles.getString("name");
                String type = vehicles.getString("type");
                int seats = vehicles.getInt("seats");
                vehicleDetails.append("ID: ").append(id).append(", Name: ").append(name).append(", Type: ").append(type).append(", Seats: ").append(seats).append("\n");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching available vehicles: " + e.getMessage());
        }
        return vehicleDetails.toString();
    }

    public double getRatePerDay() {
        return RATE_PER_DAY;
    }

    public String bookVehicle(Customer customer, String vehicleID, String startDateStr, String endDateStr) {
        try {
            if (dbManager.isVehicleAvailable(vehicleID)) {
                boolean bookingSuccessful = dbManager.bookVehicleForCustomer(vehicleID, customer, startDateStr, endDateStr);
                if (bookingSuccessful) {
                    // Calculate the number of days between the start date and end date
                    LocalDate startDate = LocalDate.parse(startDateStr);
                    LocalDate endDate = LocalDate.parse(endDateStr);
                    long numberOfDays = ChronoUnit.DAYS.between(startDate, endDate);

                    // Calculate the total cost
                    double totalCost = numberOfDays * RATE_PER_DAY;

                    // Include the total cost in the confirmation message
                    return "Booking successful for Vehicle ID: " + vehicleID + ". Total cost: Rs " + totalCost;
                } else {
                    return "Booking failed for Vehicle ID: " + vehicleID;
                }
            } else {
                return "Vehicle ID: " + vehicleID + " is not available.";
            }
        } catch (SQLException e) {
            return "Error booking vehicle: " + e.getMessage();
        }
    }

    public void returnVehicle(String vehicleID) {
        dbManager.returnVehicle(vehicleID);
        System.out.println("Vehicle ID " + vehicleID + " has been returned and is now available.");
    }
}