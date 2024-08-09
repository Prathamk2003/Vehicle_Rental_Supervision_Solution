package Vehicle_Rental_Supervision_Solution_Project.Vehicle_Rental_Supervision_Solution_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Vehicle_Rental_Supervision {
    private JFrame frame;
    private JTextField nameField, emailField, phoneField, vehicleIDField, startDateField, endDateField;
    private JButton bookButton;
    private JTextArea outputArea;
    private Vehicle_Manager vehicleManager;

    public Vehicle_Rental_Supervision() {
        initializeGUI();
    }

    private void initializeGUI() {
        frame = new JFrame("Vehicle Rental Supervision");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        inputPanel.setBackground(Color.LIGHT_GRAY);

        JLabel nameLabel = new JLabel("Customer Name:");
        nameField = new JTextField();
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        JLabel emailLabel = new JLabel("Customer Email:");
        emailField = new JTextField();
        inputPanel.add(emailLabel);
        inputPanel.add(emailField);

        JLabel phoneLabel = new JLabel("Customer Phone:");
        phoneField = new JTextField();
        inputPanel.add(phoneLabel);
        inputPanel.add(phoneField);

        JLabel vehicleIDLabel = new JLabel("Vehicle ID:");
        vehicleIDField = new JTextField();
        inputPanel.add(vehicleIDLabel);
        inputPanel.add(vehicleIDField);

        JLabel startDateLabel = new JLabel("Start Date (YYYY-MM-DD):");
        startDateField = new JTextField();
        inputPanel.add(startDateLabel);
        inputPanel.add(startDateField);

        JLabel endDateLabel = new JLabel("End Date (YYYY-MM-DD):");
        endDateField = new JTextField();
        inputPanel.add(endDateLabel);
        inputPanel.add(endDateField);

        bookButton = new JButton("Book Vehicle");
        bookButton.addActionListener(new BookButtonListener());
        inputPanel.add(new JLabel());
        inputPanel.add(bookButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        vehicleManager = new Vehicle_Manager();
        String vehicleDetails = vehicleManager.displayAvailableVehicles();
        outputArea.append(vehicleDetails);
        frame.setVisible(true);
    }

    private class BookButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String customerName = nameField.getText();
            String customerEmail = emailField.getText();
            String customerPhone = phoneField.getText();
            String vehicleID = vehicleIDField.getText();
            String startDateStr = startDateField.getText();
            String endDateStr = endDateField.getText();

            Customer customer = new Customer(customerName, customerEmail, customerPhone);

            try {
                LocalDate startDate = LocalDate.parse(startDateStr);
                LocalDate endDate = LocalDate.parse(endDateStr);

                String bookingMessage = vehicleManager.bookVehicle(customer, vehicleID, startDateStr, endDateStr);

                outputArea.append(bookingMessage + "\n");
            } catch (DateTimeParseException ex) {
                outputArea.append("Error: Invalid date format. Please enter date in YYYY-MM-DD format.\n");
            } catch (Exception ex) {
                outputArea.append("An error occurred: " + ex.getMessage() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();
        dbManager.setAllVehiclesAvailable();
        new Vehicle_Rental_Supervision();
    }
}