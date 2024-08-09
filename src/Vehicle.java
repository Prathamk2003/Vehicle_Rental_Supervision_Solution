package Vehicle_Rental_Supervision_Solution_Project.Vehicle_Rental_Supervision_Solution_Project;

public class Vehicle {
    private String id;
    private String name;
    private String type;
    private int seats;
    private String status_aval;

    // Constructor accepting id, name, type, and seats
    public Vehicle(String id, String name, String type, int seats, String status_aval) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.seats = seats;
        this.status_aval = status_aval;
    }

    // Getters for the properties
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getSeats() {
        return seats;
    }

    public String get_status_aval() {
        return status_aval;
    }
}