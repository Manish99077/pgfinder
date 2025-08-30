package controller;

import Model.PGListings;
import Model.User;
import service.PGListingService;

import java.util.List;
import java.util.Scanner;

public class PGListingController {
    private Scanner sc = new Scanner(System.in);
    private PGListingService service = new PGListingService();
    private User currentUser;
    private boolean exit = false;

    // Constants for menu options
    private static final int ADMIN_ADD = 1;
    private static final int ADMIN_UPDATE = 2;
    private static final int ADMIN_DELETE = 3;
    private static final int ADMIN_VIEW_ALL = 4;
    private static final int ADMIN_BOOKINGS = 5;
    private static final int ADMIN_EXIT = 6;

    private static final int OWNER_ADD = 1;
    private static final int OWNER_UPDATE = 2;
    private static final int OWNER_VIEW_MY = 3;
    private static final int OWNER_BOOKINGS = 4;
    private static final int OWNER_EXIT = 5;

    private static final int TENANT_VIEW_ALL = 1;
    private static final int TENANT_BOOKINGS = 2;
    private static final int TENANT_EXIT = 3;

    public PGListingController(User currentUser) {
        this.currentUser = currentUser;
    }

    public void start() {
        while (!exit) {
            System.out.println("\n-- PG Listings Menu --");
            showMenuByRole();

            System.out.print("Enter choice: ");
            int choice = getValidChoice();

            handleChoice(choice);
        }
    }

    private int getValidChoice() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return getValidChoice(); // Recursive call for valid input
        }
    }

    private void showMenuByRole() {
        String role = currentUser.getRole();
        switch (role) {
            case "admin":
                System.out.println(ADMIN_ADD + ". Add PG Listing");
                System.out.println(ADMIN_UPDATE + ". Update PG Listing");
                System.out.println(ADMIN_DELETE + ". Delete PG Listing");
                System.out.println(ADMIN_VIEW_ALL + ". View All PG Listings");
                System.out.println(ADMIN_BOOKINGS + ". Go to Bookings Menu");
                System.out.println(ADMIN_EXIT + ". Exit");
                break;
            case "owner":
                System.out.println(OWNER_ADD + ". Add PG Listing");
                System.out.println(OWNER_UPDATE + ". Update PG Listing");
                System.out.println(OWNER_VIEW_MY + ". View My PG Listings");
                System.out.println(OWNER_BOOKINGS + ". Go to Bookings Menu");
                System.out.println(OWNER_EXIT + ". Exit");
                break;
            case "tenant":
                System.out.println(TENANT_VIEW_ALL + ". View All PG Listings");
                System.out.println(TENANT_BOOKINGS + ". Go to Bookings Menu");
                System.out.println(TENANT_EXIT + ". Exit");
                break;
            default:
                System.out.println("Invalid role.");
                exit = true;
        }
    }

    private void handleChoice(int choice) {
        String role = currentUser.getRole();

        if ("admin".equals(role)) {
            handleAdminChoice(choice);
        } else if ("owner".equals(role)) {
            handleOwnerChoice(choice);
        } else if ("tenant".equals(role)) {
            handleTenantChoice(choice);
        }
    }

    private void handleAdminChoice(int choice) {
        switch (choice) {
            case ADMIN_ADD:
                addPGListing();
                break;
            case ADMIN_UPDATE:
                updatePGListing();
                break;
            case ADMIN_DELETE:
                deletePGListing();
                break;
            case ADMIN_VIEW_ALL:
                viewAllPGListings();
                break;
            case ADMIN_BOOKINGS:
                goToBookings();
                break;
            case ADMIN_EXIT:
                exit = true;
                break;
            default:
                System.out.println("Invalid choice for admin.");
        }
    }

    private void handleOwnerChoice(int choice) {
        switch (choice) {
            case OWNER_ADD:
                addPGListing();
                break;
            case OWNER_UPDATE:
                updatePGListing();
                break;
            case OWNER_VIEW_MY:
                viewMyPGListings();
                break;
            case OWNER_BOOKINGS:
                goToBookings();
                break;
            case OWNER_EXIT:
                exit = true;
                break;
            default:
                System.out.println("Invalid choice for owner.");
        }
    }

    private void handleTenantChoice(int choice) {
        switch (choice) {
            case TENANT_VIEW_ALL:
                viewAllPGListings();
                break;
            case TENANT_BOOKINGS:
                goToBookings();
                break;
            case TENANT_EXIT:
                exit = true;
                break;
            default:
                System.out.println("Invalid choice for tenant.");
        }
    }

    private void goToBookings() {
        BookingsController bc = new BookingsController(currentUser);
        bc.start();
    }

    private void addPGListing() {
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter City: ");
        String city = sc.nextLine();
        System.out.print("Enter Rent: ");
        double rent = getValidDouble();
        System.out.print("Enter Description: ");
        String description = sc.nextLine();

        PGListings pg = new PGListings(currentUser.getId(), title, city, rent, description);
        boolean added = service.addPGListing(pg);

        if (added) {
            System.out.println("PG Listing added successfully.");
        } else {
            System.out.println("Failed to add PG Listing.");
        }
    }

    private void updatePGListing() {
        System.out.print("Enter PG Listing ID to update: ");
        int id = getValidInt();

        PGListings existing = service.getPGListingById(id);
        if (existing == null) {
            System.out.println("PG Listing not found.");
            return;
        }

        if ("owner".equals(currentUser.getRole()) && existing.getOwnerId() != currentUser.getId()) {
            System.out.println("You can update only your own listings.");
            return;
        }

        System.out.print("Enter new Title: ");
        String title = sc.nextLine();
        System.out.print("Enter new City: ");
        String city = sc.nextLine();
        System.out.print("Enter new Rent: ");
        double rent = getValidDouble();
        System.out.print("Enter new Description: ");
        String description = sc.nextLine();

        existing.setTitle(title);
        existing.setCity(city);
        existing.setRent(rent);
        existing.setDescription(description);

        boolean updated = service.updatePGListing(existing);
        if (updated) {
            System.out.println("PG Listing updated successfully.");
        } else {
            System.out.println("Failed to update PG Listing.");
        }
    }

    private void deletePGListing() {
        System.out.print("Enter PG Listing ID to delete: ");
        int id = getValidInt();

        boolean deleted = service.deletePGListing(id);
        System.out.println(deleted ? "PG Listing deleted successfully." : "Failed to delete PG Listing.");
    }

    private void viewAllPGListings() {
        List<PGListings> list = service.getAllPGListings();
        if (list.isEmpty()) {
            System.out.println("No PG Listings found.");
        } else {
            list.forEach(System.out::println);
        }
    }

    private void viewMyPGListings() {
        List<PGListings> list = service.getPGListingsByOwnerId(currentUser.getId());
        if (list.isEmpty()) {
            System.out.println("You have no PG Listings.");
        } else {
            list.forEach(System.out::println);
        }
    }

    private int getValidInt() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return getValidInt();
        }
    }

    private double getValidDouble() {
        try {
            return Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return getValidDouble();
        }
    }
}