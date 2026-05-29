package com.pluralsight.ui;

import com.pluralsight.model.*;
import com.pluralsight.service.ChipsService;
import com.pluralsight.service.DrinkService;
import com.pluralsight.service.SandwichService;
import com.pluralsight.util.ReceiptWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in); // Scanner
    private final MenuService menuService = new MenuService(scanner); // Menu Services
    private final SpecialtySandwichRegistry specialtyRegistry = new SpecialtySandwichRegistry(); // Specialty Sandwich Registry
    private final SandwichService sandwichService = new SandwichService(); // Sandwich Services
    private final DrinkService drinkService = new DrinkService(); // Drink Services
    private final ChipsService chipsService = new ChipsService(); // Chips Services

    // Displays menu and navigation to create new orders or exit
    public void display() {
        while (true) {
            System.out.printf(
                    "Commands:%n" +
                            "1. New Order%n" +
                            "0. Exit%n" +
                            "What do you want to do: "
            );

            switch (scanner.nextLine()) {
                case "1" -> createNewOrder();
                case "0" -> {
                    System.out.printf("%nThank you for being our valued customer!%n");
                    return;
                }
                default -> System.out.printf("Invalid option%n%n");
            }
        }
    }

    // Creates new orders
    // Allows user to add items, review order, or checkout until the order is completed or canceled
    public void createNewOrder() {
        Order order = new Order();

        boolean ordering = true;

        while (ordering) {

            System.out.printf(
                    "\n===== ORDER MENU =====%n" +
                    "1. Add Sandwich%n" +
                    "2. Add Speciality Sandwich%n" +
                    "3. Add Drink%n" +
                    "4. Add Chips%n" +
                    "5. Check Order%n" +
                    "6. Checkout%n" +
                    "0. Cancel Order%n" +
                    "Choose an option: "
            );

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addSandwich(order);
                    break;
                case 2:
                    addSpecialtySandwich(order);
                    break;
                case 3:
                    addDrink(order);
                    break;
                case 4:
                    addChips(order);
                    break;
                case 5:
                    checkOrder(order);
                    break;
                case 6:
                    checkout(order);
                    ordering = false;
                    break;
                case 0:
                    System.out.printf("Order cancelled%n%n");
                    ordering = false;
                    break;
                default:
                    System.out.printf("Invalid option%n");
            }
        }
    }

    // Calls SandwichService to build sandwich inside MenuService
    public void addSandwich(Order order) {
        int sandwichSize = menuService.selectSandwichSize();
        if (sandwichSize == 0) return;

        String breadType = menuService.selectBreadType();
        boolean isToasted = menuService.selectToasted();
        List<Topping> toppings = menuService.selectToppings(sandwichSize);

        Sandwich sandwich = sandwichService.createSandwich(
                sandwichSize,
                breadType,
                isToasted,
                new ArrayList<>(toppings)
        );

        order.addSandwich(sandwich);

        System.out.println("Sandwich has been added.");
    }

    // Adds prebuilt specialty sandwich to the order using a registry of SandwichBuilder
    private void addSpecialtySandwich(Order order) {

        System.out.println("\n===== SPECIALTY SANDWICHES =====");
        System.out.println("1. BLT");
        System.out.println("2. Philly Cheese Steak");
        System.out.println("0. Exit");
        System.out.print("Choose: ");

        String choice = scanner.nextLine();

        SandwichBuilder builder = specialtyRegistry.get(choice);

        if (builder == null) {
            System.out.println("Cancelled or invalid option.");
            return;
        }

        Sandwich sandwich = builder.build();
        order.addSandwich(sandwich);

        System.out.println("Specialty sandwich added.");
    }

    // User input drink size and flavor, creates drink using DrinkService, adds to order
    public void addDrink(Order order) {
        String drinkSize;
        String drinkType;

        System.out.printf("%n===== DRINK =====");

        while (true) {
            System.out.printf(
                    "%n1. Small%n" +
                            "2. Medium%n" +
                            "3. Large%n" +
                            "0. Exit%n" +
                            "Drinks: "
            );

            switch (scanner.nextLine()) {
                case "1":
                    drinkSize = "small";
                    break;
                case "2":
                    drinkSize = "medium";
                    break;
                case "3":
                    drinkSize = "large";
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option");
                    continue;
            }
            break;
        }

        System.out.print("\nFlavor: ");
        drinkType = scanner.nextLine();

        Drink drink = drinkService.createDrink(drinkSize, drinkType);
        order.addDrink(drink);

        System.out.println("Drink has been added.");
    }

    // User input chips flavor, creates chips using ChipsService, adds to order
    public void addChips(Order order) {
        System.out.printf("%n===== CHIPS =====");

        while (true) {
            System.out.printf(
                    "%n1. Add Chips%n" +
                            "0. Exit%n" +
                            "Chips: "
            );

            switch (scanner.nextLine()) {

                case "1":
                    System.out.print("\nChip flavor: ");
                    String chipsType = scanner.nextLine();

                    Chips chips = chipsService.createChips(chipsType);
                    order.addChip(chips);

                    System.out.println("Chips have been added.");
                    return;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    // Displays  order for user review before checkout
    public void checkOrder(Order order) {
        System.out.print("\n===== ORDER =====");

        for (Sandwich s : order.getSandwiches()) {
            System.out.println();
            System.out.println(s);
        }

        for (Drink d : order.getDrinks()) {
            System.out.println();
            System.out.println(d);
        }

        for (Chips c : order.getChips()) {
            System.out.println();
            System.out.println(c);
        }
    }

    // Displays full order and total price, then allows user to confirm or cancel the order
    // If confirmed, receipt is generated and saved to a file inside deli-cious/receipts
    public void checkout(Order order) {
        if (!order.getSandwiches().isEmpty() || !order.getDrinks().isEmpty() || !order.getChips().isEmpty()) {
            System.out.print("\n===== CHECKOUT =====");

            for (Sandwich s : order.getSandwiches()) {
                System.out.println();
                System.out.println(s);
            }

            for (Drink d : order.getDrinks()) {
                System.out.println();
                System.out.println(d);
            }

            for (Chips c : order.getChips()) {
                System.out.println();
                System.out.println(c);
            }

            System.out.printf("%nTOTAL: $%.2f%n", order.calculateTotal());

            System.out.println("\n1) Confirm Order");
            System.out.println("0) Cancel Order");
            System.out.print("Choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    ReceiptWriter writer = new ReceiptWriter();
                    writer.writeReceipt(order);
                    System.out.printf("Order confirmed! Receipt saved%n%n");
                case 0:
                    System.out.printf("Order cancelled%n");
                default:
                    System.out.printf("Invalid choice. Returning to menu%n");
            }
        } else {
            System.out.printf("Error! Order must contain chips or a drink!%n%n");
        }
    }
}