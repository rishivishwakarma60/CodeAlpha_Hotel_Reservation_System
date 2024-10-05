package code_alpha;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Room class to represent a hotel room
class Room {
    private String category;
    private double price;
    private boolean isAvailable;

    public Room(String category, double price) {
        this.category = category;
        this.price = price;
        this.isAvailable = true;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Room [category=" + category + ", price=" + price + ", available=" + isAvailable + "]";
    }
}

// Hotel class to manage rooms and reservations
class Hotel {
    private List<Room> rooms = new ArrayList<>();

    public Hotel() {
        // Add rooms to the hotel
        rooms.add(new Room("Standard", 100.00));
        rooms.add(new Room("Deluxe", 150.00));
        rooms.add(new Room("Suite", 200.00));
    }

    public List<Room> getAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Room bookRoom(String category) {
        for (Room room : rooms) {
            if (room.getCategory().equalsIgnoreCase(category) && room.isAvailable()) {
                room.setAvailable(false);
                return room;
            }
        }
        return null;
    }

    public void displayAvailableRooms() {
        System.out.println("Available rooms:");
        for (Room room : getAvailableRooms()) {
            System.out.println(room);
        }
    }
}

// Reservation class to hold booking details
class Reservation {
    private Room room;
    private String guestName;

    public Reservation(Room room, String guestName) {
        this.room = room;
        this.guestName = guestName;
    }

    public void displayReservationDetails() {
        System.out.println("Reservation Details:");
        System.out.println("Guest Name: " + guestName);
        System.out.println("Room Category: " + room.getCategory());
        System.out.println("Price: $" + room.getPrice());
    }
}

// Payment class to simulate payment processing
class Payment {
    public static boolean processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + "...");
        // In a real application, integrate with a payment gateway here
        System.out.println("Payment successful!");
        return true;
    }
}

// Main class for the Hotel Reservation System
public class HotelReservationSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Hotel hotel = new Hotel();
    private static List<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. Search Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Booking Details");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    searchAvailableRooms();
                    break;
                case 2:
                    makeReservation();
                    break;
                case 3:
                    viewBookingDetails();
                    break;
                case 4:
                    System.out.println("Thank you for using the Hotel Reservation System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

    // Search available rooms
    private static void searchAvailableRooms() {
        hotel.displayAvailableRooms();
    }

    // Make a reservation
    private static void makeReservation() {
        System.out.print("Enter your name: ");
        String guestName = scanner.nextLine();

        System.out.print("Enter room category (Standard, Deluxe, Suite): ");
        String category = scanner.nextLine();

        Room room = hotel.bookRoom(category);
        if (room != null) {
            double price = room.getPrice();
            if (Payment.processPayment(price)) {
                Reservation reservation = new Reservation(room, guestName);
                reservations.add(reservation);
                System.out.println("Reservation successful!");
            }
        } else {
            System.out.println("Sorry, no available rooms in the chosen category.");
        }
    }

    // View booking details
    private static void viewBookingDetails() {
        if (reservations.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            for (Reservation reservation : reservations) {
                reservation.displayReservationDetails();
            }
        }
    }
}
