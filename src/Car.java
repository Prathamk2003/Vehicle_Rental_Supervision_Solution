package Vehicle_Rental_Supervision_Solution_Project.Vehicle_Rental_Supervision_Solution_Project;

public class Car extends Vehicle {
    private final int numberOfSeats;

    // Constructor for Car class
    public Car(String vehicleID, String name, int numberOfSeats) {
        super(vehicleID, name, "Car", numberOfSeats, "available"); // Call to superclass constructor
        this.numberOfSeats = numberOfSeats;
    }

    // Getter method for numberOfSeats
    public int getNumberOfSeats() {
        return numberOfSeats;
    }
}