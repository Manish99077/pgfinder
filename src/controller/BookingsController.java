package controller;

import Model.Bookings;
import Model.User;
import service.BookingsService;

import java.util.List;
import java.util.Scanner;

public class BookingsController {
    private Scanner sc = new Scanner(System.in);
    private BookingsService service = new BookingsService();
    private User currentUser;
    private boolean exit = false;

    public BookingsController(User currentUser) {
        this.currentUser = currentUser;
    }

    public void start() {
        while (!exit) {
            System.out.println("\n-- Bookings Menu --");
            showMenuByRole();

            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            handleChoice(choice);
        }
    }

    private void showMenuByRole() {
        String role = currentUser.getRole();

        if ("admin".equals(role)) {
            System.out.println("1. View All Bookings");
            System.out.println("2. Update Booking Status");
            System.out.println("3. Delete Booking");
            System.out.println("4. Exit");
        } else if ("owner".equals(role)) {
            System.out.println("1. View Bookings for My PGs");
            System.out.println("2. Exit");
        } else if ("tenant".equals(role)) {
            System.out.println("1. Add Booking");
            System.out.println("2. View My Bookings");
            System.out.println("3. Exit");
        } else {
            System.out.println("Invalid role.");
            exit = true;
        }
    }

    private void handleChoice(int choice) {
        String role = currentUser.getRole();

        if ("admin".equals(role)) {
            switch (choice) {
                case 1: viewAllBookings(); break;
                case 2: updateBookingStatus(); break;
                case 3: deleteBooking(); break;
                case 4: exit = true; break;
                default: System.out.println("Invalid choice.");
            }
        } else if ("owner".equals(role)) {
            switch (choice) {
                case 1: viewBookingsForMyPGs(); break;
                case 2: exit = true; break;
                default: System.out.println("Invalid choice.");
            }
        } else if ("tenant".equals(role)) {
            switch (choice) {
                case 1: addBooking(); break;
                case 2: viewMyBookings(); break;
                case 3: exit = true; break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private void addBooking() {
        System.out.print("Enter PG Listing ID to book: ");
        int pgId = Integer.parseInt(sc.nextLine());

        Bookings booking = new Bookings(pgId, currentUser.getId(), "Pending");
        boolean added = service.addBooking(booking);

        if (added) {
            System.out.println("Booking request added successfully.");
        } else {
            System.out.println("Failed to add booking.");
        }
    }

    private void viewMyBookings() {
        List<Bookings> bookings = service.getBookingsByTenantId(currentUser.getId());

        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            for (Bookings booking : bookings) {
                System.out.println(booking);
            }
        }
    }

    private void viewBookingsForMyPGs() {
        List<Bookings> bookings = service.getBookingsForOwner(currentUser.getId());

        if (bookings.isEmpty()) {
            System.out.println("No bookings found for your PG listings.");
        } else {
            for (Bookings booking : bookings) {
                System.out.println(booking);
            }
        }
    }

    private void viewAllBookings() {
        List<Bookings> bookings = service.getAllBookings();

        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            for (Bookings booking : bookings) {
                System.out.println(booking);
            }
        }
    }

    private void updateBookingStatus() {
        System.out.print("Enter Booking ID to update: ");
        int bookingId = Integer.parseInt(sc.nextLine());

        Bookings booking = service.getBookingById(bookingId);
        if (booking == null) {
            System.out.println("Booking not found.");
            return;
        }

        System.out.print("Enter new status (Pending/Confirmed/Cancelled): ");
        String status = sc.nextLine();

        booking.setStatus(status);
        boolean updated = service.updateBooking(booking);

        if (updated) {
            System.out.println("Booking status updated.");
        } else {
            System.out.println("Failed to update booking.");
        }
    }

    private void deleteBooking() {
        System.out.print("Enter Booking ID to delete: ");
        int bookingId = Integer.parseInt(sc.nextLine());

        boolean deleted = service.deleteBooking(bookingId);

        if (deleted) {
            System.out.println("Booking deleted.");
        } else {
            System.out.println("Failed to delete booking.");
        }
    }
}