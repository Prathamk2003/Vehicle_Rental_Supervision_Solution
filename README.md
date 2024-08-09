# Vehicle Rental Supervision System

## Project Overview
This project implements a Vehicle Rental Supervision System, allowing customers to book vehicles for specified date ranges. The system manages vehicle inventory, customer bookings, and provides a graphical user interface for interacting with the rental service.

## Components

### 1. Vehicle_Rental_Supervision
- Main class with GUI implementation
- Allows users to input customer details, select vehicles, and make bookings
- Displays available vehicles and booking confirmations

### 2. Vehicle_Manager
- Manages vehicle-related operations
- Displays available vehicles
- Handles vehicle booking and return processes
- Calculates rental costs

### 3. DatabaseManager
- Manages database connections and operations
- Performs CRUD operations on vehicles and bookings
- Checks vehicle availability and updates vehicle status

### 4. Vehicle
- Base class for all vehicle types
- Stores vehicle properties like ID, name, type, seats, and availability status

### 5. Car
- Extends the Vehicle class
- Specifically represents car type vehicles with additional properties like number of seats

### 6. Customer
- Represents a customer with properties like name, email, and phone number

### 7. Billing_Thread
- Implements a separate thread for billing calculations
- Simulates the billing process with a delay

### 8. Exception_Handling
- Custom exception class for handling project-specific exceptions

### 9. Database Schema
- Includes SQL scripts for creating the necessary database tables (Vehicles and Bookings)

## Features
- User-friendly GUI for vehicle rental management
- Real-time display of available vehicles
- Customer registration and vehicle booking
- Automatic calculation of rental costs
- Database integration for persistent data storage
- Custom exception handling
- Multithreaded billing process

## Setup and Running the Application
1. Ensure you have Java and MySQL installed on your system.
2. Set up the database using the provided SQL scripts in Vehicle_Database.sql.
3. Update the database connection details in DatabaseManager.java.
4. Compile and run the Vehicle_Rental_Supervision.java file to start the application.

## Dependencies
- Java Swing for GUI
- JDBC for database connectivity

## Output
![WhatsApp Image 2024-08-09 at 19 35 16_93e77647](https://github.com/user-attachments/assets/170dd61f-493c-430f-aa98-9d9c127c7e06)

![WhatsApp Image 2024-08-09 at 19 35 42_231fe90e](https://github.com/user-attachments/assets/0e661252-ed31-4c77-88a8-f202ecad8ea5)


## Future Improvements
- Implement user authentication
- Add more vehicle types
- Enhance error handling and input validation
- Implement a more sophisticated billing system
- Add reporting and analytics features
