import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OnlineReservationSystem {
    private Map<String, String> users; 
    private Map<String, String> reservations; 

    public OnlineReservationSystem() {
        users = new HashMap<>();
        reservations = new HashMap<>();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            displayMainMenu();
            int choice = getValidChoice(sc, 1, 3);

            switch (choice) {
                case 1:
                    register(sc);
                    break;
                case 2:
                    login(sc);
                    break;
                case 3:
                    exit();
                    return;
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("------------------------------------------------");
        System.out.println("----- WELCOME TO ONLINE RESERVATION SYSTEM -----");
        System.out.println("------------------------------------------------");
        System.out.println("Please Select One Option..." + "\n");
        System.out.println("1.>>> Register >>>");
        System.out.println("2.>>> Login >>>");
        System.out.println("3.>>> Exit >>>");
        System.out.println("------------------------------------------------");
        System.out.print("Enter your choice (1/2/3): ");
    }

    private void register(Scanner sc) {
        System.out.println("------------------------------------------------");
        System.out.println("--------------- REGISTRATION PAGE --------------");
        System.out.println("------------------------------------------------");
        System.out.print("Enter username: ");
        String username = sc.nextLine();

        if (users.containsKey(username)) {
            System.out.println("\nUsername already exists. Try again...");
            return;
        }

        System.out.print("Enter password: ");
        String password = sc.nextLine();
        users.put(username, password);
        System.out.println("\nRegistration successful! You can now log in...");
    }

    private void login(Scanner sc) {
        System.out.println("------------------------------------------------");
        System.out.println("------------------ LOGIN PAGE ------------------");
        System.out.println("------------------------------------------------");
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            System.out.println("\nLogin successful!");
            reservationMenu(sc, username);
        } else {
            System.out.println("\nInvalid username or password. Please try again...");
        }
    }

    private void reservationMenu(Scanner sc, String username) {
        while (true) {
            displayReservationMenu();
            int choice = getValidChoice(sc, 1, 4);

            switch (choice) {
                case 1:
                    makeReservation(sc, username);
                    break;
                case 2:
                    cancelReservation(sc, username);
                    break;
                case 3:
                    viewReservation(username);
                    break;
                case 4:
                    System.out.println("\nLogging out...");
                    return;
            }
        }
    }

    private void displayReservationMenu() {
        System.out.println("------------------------------------------------");
        System.out.println("------------------- HOME PAGE ------------------");
        System.out.println("------------------------------------------------");
        System.out.println("Please Select One Option..." + "\n");
        System.out.println("1.>>> Make a reservation >>>");
        System.out.println("2.>>> Cancel a reservation >>>");
        System.out.println("3.>>> View reservation >>>");
        System.out.println("4.>>> Logout");
        System.out.println("------------------------------------------------");
        System.out.print("Enter your choice (1/2/3/4): ");
    }

    private void makeReservation(Scanner sc, String username) {
        System.out.println("------------------------------------------------");
        System.out.print("Enter reservation details: ");
        String reservationDetails = sc.nextLine();

        if (reservations.containsKey(username)) {
            System.out.println("\nYou already have a reservation. Cancel it first to make a new one...");
            return;
        }

        reservations.put(username, reservationDetails);
        System.out.println("\nReservation created successfully!");
    }

    private void cancelReservation(Scanner sc, String username) {
        if (reservations.containsKey(username)) {
            System.out.println("------------------------------------------------");
            System.out.println("Your current reservation: " + reservations.get(username));
            System.out.print("Do you want to cancel this reservation? (Y/N): ");
            String confirmation = sc.nextLine();

            if (confirmation.equalsIgnoreCase("Y")) {
                reservations.remove(username);
                System.out.println("\nReservation cancelled successfully.");
            } else {
                System.out.println("\nReservation not cancelled.");
            }
        } else {
            System.out.println("\nYou don't have any reservations.");
        }
    }

    private void viewReservation(String username) {
        if (reservations.containsKey(username)) {
            System.out.println("------------------------------------------------");
            System.out.println("Your current reservation: " + reservations.get(username));
            System.out.println("------------------------------------------------");
        } else {
            System.out.println("\nYou don't have any reservations.");
        }
    }

    private int getValidChoice(Scanner sc, int min, int max) {
        int choice;
        while (true) {
            try {
                choice = sc.nextInt();
                sc.nextLine(); // Consume newline
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.print("Invalid choice. Please enter a number between " + min + " and " + max + ": ");
                }
            } catch (Exception e) {
                sc.nextLine(); // Clear invalid input
                System.out.print("Invalid input. Please enter a number between " + min + " and " + max + ": ");
            }
        }
    }

    private void exit() {
        System.out.println("------------------------------------------------");
        System.out.println("Exiting...");
        System.out.println("Thank You! Please Visit Again...");
        System.out.println("------------------------------------------------");
    }

    public static void main(String[] args) {
        OnlineReservationSystem system = new OnlineReservationSystem();
        system.run();
    }
}