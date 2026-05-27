package com.pluralsight.ui;

import com.pluralsight.models.*;
import com.pluralsight.util.ReceiptWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private List<Sandwich> sandwiches = new ArrayList<>();
    private List<Drink> drinks = new ArrayList<>();
    private List<Chips> chips = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public void display() {

        while (true) {
            System.out.printf(
                    "Commands:%n" +
                            "1 - New Order%n" +
                            "0 - Exit%n" +
                            "What do you want to do: "
            );

            switch (scanner.nextLine()) {
                case "1" -> createNewOrder();
                case "0" -> {
                    System.out.printf("%nThank you for being our valued customer!%n");
                    return;
                }
                default -> System.out.printf("Invalid option%n");
            }
        }
    }

    public void createNewOrder() {

        Order order = new Order();

        boolean ordering = true;

        while (ordering) {

            System.out.println("\n===== ORDER MENU =====");
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addSandwich(order);
                    break;

                case 2:
                    addDrink(order);
                    break;

                case 3:
                    addChips(order);
                    break;

                case 4:
                    checkout(order);
                    ordering = false;
                    break;

                case 0:
                    System.out.println("Order cancelled.");
                    ordering = false;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public void addSandwich(Order order) {
        List<Topping> toppings = new ArrayList<>();
        toppings.add(new Topping("steak", "meat", false, 12));

        Sandwich sandwich = new Sandwich(8, "white", true, new ArrayList<>(toppings));

        order.addSandwich(sandwich);

        System.out.println("Sandwich has been added.");
    }

    public void addDrink(Order order) {
        Drink drink = new Drink("medium", "coca cola");

        order.addDrink(drink);

        System.out.println("Drink has been added.");
    }

    public void addChips(Order order) {
        Chips chips = new Chips("lays");

        order.addChip(chips);

        System.out.println("Chips has been added.");
    }

    public void checkout(Order order) {

        System.out.println("\n===== CHECKOUT =====");

        System.out.println(order);
        for (Sandwich s : order.getSandwiches()) {
            System.out.println(s);
        }

        System.out.printf("TOTAL: $%.2f%n", order.calculateTotal());

        System.out.println("\n1) Confirm Order");
        System.out.println("0) Cancel Order");
        System.out.print("Choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {

            case 1 -> {
                ReceiptWriter writer = new ReceiptWriter();
                writer.writeReceipt(order);
                System.out.println("Order confirmed! Receipt saved.");
            }

            case 0 -> {
                System.out.println("Order cancelled.");
            }

            default -> {
                System.out.println("Invalid choice. Returning to menu.");
            }
        }
    }

    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public List<Chips> getChips() {
        return chips;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("===== ORDER SUMMARY =====\n");

        sb.append("\n--- Sandwiches ---\n");
        for (Sandwich s : sandwiches) {
            sb.append(s).append("\n\n");
        }

        sb.append("\n--- Drinks ---\n");
        for (Drink d : drinks) {
            sb.append(d).append("\n");
        }

        sb.append("\n--- Chips ---\n");
        for (Chips c : chips) {
            sb.append(c).append("\n");
        }

        return sb.toString();
    }
}
