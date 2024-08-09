package Vehicle_Rental_Supervision_Solution_Project.Vehicle_Rental_Supervision_Solution_Project;

public class Billing_Thread extends Thread {
    private final int numberOfDays;
    private final double ratePerDay;

    public Billing_Thread(int numberOfDays, double ratePerDay) {
        this.numberOfDays = numberOfDays;
        this.ratePerDay = ratePerDay;
    }

    @Override
    public void run() {
        try {
            double totalAmount = ratePerDay * numberOfDays;
            Thread.sleep(2000); // Simulate billing process
            System.out.println("Billing completed.");
            System.out.println("Total amount for " + numberOfDays + " days: Rs " + totalAmount);
        } catch (InterruptedException e) {
            System.err.println("Billing process was interrupted: " + e.getMessage());
        }
    }
}
