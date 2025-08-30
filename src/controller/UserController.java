package controller;

import Model.User;
import service.UserService;

import java.util.Scanner;

public class UserController {
    private Scanner sc = new Scanner(System.in);
    private UserService service = new UserService();
    private boolean exit = false;

    public void start() {
        while (!exit) {
            System.out.println("\n-- User Menu --");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void register() {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();
        System.out.print("Enter Role (admin/owner/tenant): ");
        String role = sc.nextLine();

        User user = new User(name, email, password, role);
        boolean registered = service.registerUser(user);

        if (registered) {
            System.out.println("User registered successfully.");
        } else {
            System.out.println("Failed to register user.");
        }
    }

    private void login() {
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        User user = service.loginUser(email, password);
        if (user != null) {
            System.out.println("Login successful.");
            PGListingController plc = new PGListingController(user);
            plc.start();
        } else {
            System.out.println("Invalid credentials.");
        }
    }
}