package com.pluralsight.ui;

import com.pluralsight.model.*;
import com.pluralsight.util.ReceiptWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private List<Sandwich> sandwiches = new ArrayList<>();
    private List<Drink> drinks = new ArrayList<>();
    private List<Chips> chips = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private final MenuService menuService = new MenuService(scanner);
    private final SpecialtySandwichRegistry specialtyRegistry = new SpecialtySandwichRegistry();

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

    public void addSandwich(Order order) {
        int sandwichSize = 0;
        String breadType = null;
        boolean isToasted;
        List<Topping> toppings = new ArrayList<>();

        sandwichSize = menuService.selectSandwichSize();
        breadType = menuService.selectBreadType();
        isToasted = menuService.selectToasted();
        toppings = menuService.selectToppings(sandwichSize);

        if (sandwichSize != 0) {
            Sandwich sandwich = new Sandwich(sandwichSize, breadType, isToasted, new ArrayList<>(toppings));

            order.addSandwich(sandwich);

            System.out.println("Sandwich has been added.");
        }
    }

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

    public void addDrink(Order order) {
        String drinkSize = "";
        String drinkType = "";

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

        Drink drink = new Drink(drinkSize, drinkType);

        order.addDrink(drink);

        System.out.println("Drink has been added.");
    }

    public void addChips(Order order) {

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

                    Chips chips = new Chips(chipsType);

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

                case 1 -> {
                    ReceiptWriter writer = new ReceiptWriter();
                    writer.writeReceipt(order);
                    System.out.printf("Order confirmed! Receipt saved%n%n");
                }

                case 0 -> {
                    System.out.printf("Order cancelled%n");
                }

                default -> {
                    System.out.printf("Invalid choice. Returning to menu%n");
                }
            }
        } else {
            System.out.printf("Error! Order must contain chips or a drink!%n%n");
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

}