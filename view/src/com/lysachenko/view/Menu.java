package com.lysachenko.view;

import com.lysachenko.service.UserService;
import com.lysachenko.service.impl.UserServiceImpl;

import java.util.Scanner;

public class Menu {
    private UserService userService = new UserServiceImpl();
    private Scanner scanner = new Scanner(System.in);
    private String[] items = {
            "1. Create user",
            "2. Show users",
            "3. Update user",
            "4. Delete user",
            "(another key). Exit"};

    public void show() {
        var res = 0;
        do {
            showItems();
            System.out.println("-------------");
            System.out.print("Enter your choice: ");
            String choice = scanner.next();
            res = switch (choice) {
                case "1" -> {
                    createUser();
                    yield 1;
                }
                case "2" -> {
                    showUsers();
                    yield 2;
                }
                case "3" -> {
                    updateUser();
                    yield 3;
                }
                case "4" -> {
                    deleteUser();
                    yield 4;
                }
                default -> 0;
            };
        } while (res != 0);
    }

    private void deleteUser() {
        System.out.print("Enter user ID what you need to delete: ");
        int userId = scanner.nextInt();
        userService.delete(userId);
        System.out.println("Success delete!");
    }

    private void showItems() {
        for (String item : items) {
            System.out.println("-------------");
            System.out.println(item);
        }
    }

    private void updateUser() {
        System.out.println("User update information!");
        int userId;
        boolean present;
        do {
            System.out.print("Enter user ID: ");
            userId = scanner.nextInt();
            present = userService.isPresent(userId);
            if (!present) {
                System.out.println("User with ID: " + userId + " does not exist! Try again.");
            }
        } while (!present);

        System.out.print("Enter login: ");
        String login = scanner.next();

        System.out.print("Enter password: ");
        String password = scanner.next();

        System.out.print("Enter name: ");
        String name = scanner.next();

        System.out.print("Enter surname: ");
        String surname = scanner.next();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        if (userService.update(userId, login, password, name, surname, age)) {
            System.out.println("Success!");
        } else {
            System.out.println("Error!");
        }
    }

    private void createUser() {
        System.out.println("User registration!");
        System.out.print("Enter login: ");
        String login = scanner.next();

        System.out.print("Enter password: ");
        String password = scanner.next();

        System.out.print("Enter name: ");
        String name = scanner.next();

        System.out.print("Enter surname: ");
        String surname = scanner.next();

        System.out.print("Enter age: ");
        String age = scanner.next();
        if (userService.create(login, password, name, surname, Integer.parseInt(age))) {
            System.out.println("Success!");
        } else {
            System.out.println("Error!");
        }
    }

    private void showUsers() {
        userService.showUsers();
    }
}
