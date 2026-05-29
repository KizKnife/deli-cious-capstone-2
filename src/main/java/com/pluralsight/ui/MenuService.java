package com.pluralsight.ui;

import com.pluralsight.model.Topping;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuService {

    private final Scanner scanner;

    public MenuService(Scanner scanner) {
        this.scanner = scanner;
    }

    public int selectSandwichSize() {
        while (true) {
            System.out.printf(
                    "%n1. 4\"%n" +
                            "2. 8\"%n" +
                            "3. 12\"%n" +
                            "0. Exit%n" +
                            "Sandwich Size: "
            );

            switch (scanner.nextLine()) {
                case "1":
                    System.out.println("4\" selected!");
                    return 4;
                case "2":
                    System.out.println("8\" selected!");
                    return 8;
                case "3" :
                    System.out.println("12\" selected!");
                    return 12;
                case "0":
                    System.out.printf("Exiting...%n");
                    return 0;
                default:
                    System.out.printf("Invalid option%n");
            }
        }
    }

    public String selectBreadType() {
        while (true) {
            System.out.printf(
                    "%n1. White%n" +
                            "2. Wheat%n" +
                            "3. Rye%n" +
                            "4. Wrap%n" +
                            "Bread type: "
            );

            switch (scanner.nextLine()) {
                case "1":
                    System.out.println("White bread selected!");
                    return "white";
                case "2":
                    System.out.println("Wheat bread selected!");
                    return "wheat";
                case "3":
                    System.out.println("Rye bread selected!");
                    return "Rye";
                case "4":
                    System.out.println("Wrap selected!");
                    return "wrap";
                default:
                    System.out.printf("Invalid option%n");
            }
        }
    }

    public boolean selectToasted() {
        while (true) {
            System.out.printf(
                    "%n1. Yes%n" +
                            "2. No%n" +
                            "Toasted: "
            );

            switch (scanner.nextLine()) {
                case "1":
                    System.out.println("Toasted selected!");
                    return true;
                case "2":
                    System.out.println("Not toasted selected!");
                    return false;
                default:
                    System.out.printf("Invalid option%n");
            }
        }
    }

    public List<Topping> selectToppings(int sandwichSize) {
        List<Topping> selectedToppings = new ArrayList<>();

        while (true) {
            System.out.printf(
                    "%n1. Add Topping%n" +
                    "2. Remove Topping%n" +
                    "0. Exit%n" +
                    "Toppings: "
            );

            String input = scanner.nextLine();

            switch (input) {
                case "1" -> {
                    Topping topping = selectTopping(sandwichSize);
                    if (topping != null) {
                        selectedToppings.add(topping);
                    }
                }
                case "2" -> removeTopping(selectedToppings);
                case "0" -> {
                    System.out.println("Exiting...");
                    return selectedToppings;
                }
                default -> System.out.println("Invalid option");
            }
        }
    }

    private Topping selectTopping(int sandwichSize) {
        String toppingType = selectToppingCategory();

        if ("exit".equals(toppingType)) {
            return null;
        }

        String toppingName = switch (toppingType) {
            case "meat" -> selectMeat();
            case "cheese" -> selectCheese();
            case "regular" -> selectRegularTopping();
            case "sauce" -> selectSauce();
            case "side" -> selectSide();
            default -> "exit";
        };

        if (toppingName.equals("exit")) {
            return null;
        }

        boolean isExtra = false;

        if (toppingType.equals("meat") || toppingType.equals("cheese")) {
            isExtra = askExtra();
        }

        return new Topping(
                toppingName,
                toppingType,
                isExtra,
                sandwichSize
        );
    }

    private String selectToppingCategory() {
        while (true) {
            System.out.printf(
                    "%n1. Meats%n" +
                            "2. Cheese%n" +
                            "3. Regular Toppings%n" +
                            "4. Sauces%n" +
                            "5. Sides%n" +
                            "0. Exit%n" +
                            "Toppings: "
            );

            switch (scanner.nextLine()) {
                case "1" -> { return "meat"; }
                case "2" -> { return "cheese"; }
                case "3" -> { return "regular"; }
                case "4" -> { return "sauce"; }
                case "5" -> { return "side"; }
                case "0" -> { return "exit"; }
                default -> System.out.println("Invalid option");
            }
        }
    }

    private String selectMeat() {
        while (true) {
            System.out.printf(
                    "%n1. Steak%n" +
                            "2. Ham%n" +
                            "3. Salami%n" +
                            "4. Roast Beef%n" +
                            "5. Chicken%n" +
                            "6. Bacon%n" +
                            "0. Exit%n" +
                            "Toppings: "
            );

            switch (scanner.nextLine()) {
                case "1" -> { return "steak"; }
                case "2" -> { return "ham"; }
                case "3" -> { return "salami"; }
                case "4" -> { return "roast beef"; }
                case "5" -> { return "chicken"; }
                case "6" -> { return "bacon"; }
                case "0" -> { return "exit"; }
                default -> System.out.println("Invalid option");
            }
        }
    }

    private boolean askExtra() {
        while (true) {
            System.out.printf(
                    "%n1. Yes%n" +
                            "2. No%n" +
                            "Extra: "
            );

            switch (scanner.nextLine()) {
                case "1" -> { return true; }
                case "2" -> { return false; }
                default -> System.out.println("Invalid option");
            }
        }
    }

    private String selectCheese() {
        while (true) {
            System.out.printf(
                    "%n1. American%n" +
                            "2. Provolone%n" +
                            "3. Cheddar%n" +
                            "4. Swiss%n" +
                            "0. Exit%n" +
                            "Toppings: "
            );

            switch (scanner.nextLine()) {
                case "1" -> { return "american"; }
                case "2" -> { return "provolone"; }
                case "3" -> { return "cheddar"; }
                case "4" -> { return "swiss"; }
                case "0" -> { return "exit"; }
                default -> System.out.println("Invalid option");
            }
        }
    }

    private String selectRegularTopping() {
        while (true) {
            System.out.printf(
                    "%n1. Lettuce%n" +
                            "2. Peppers%n" +
                            "3. Onions%n" +
                            "4. Tomatoes%n" +
                            "5. Jalapeños%n" +
                            "6. Cucumbers%n" +
                            "7. Pickles%n" +
                            "8. Guacamole%n" +
                            "9. Mushrooms%n" +
                            "0. Exit%n" +
                            "Toppings: "
            );

            switch (scanner.nextLine()) {
                case "1" -> { return "lettuce"; }
                case "2" -> { return "peppers"; }
                case "3" -> { return "onions"; }
                case "4" -> { return "tomatoes"; }
                case "5" -> { return "jalapeños"; }
                case "6" -> { return "cucumbers"; }
                case "7" -> { return "pickles"; }
                case "8" -> { return "guacamole"; }
                case "9" -> { return "mushrooms"; }
                case "0" -> { return "exit"; }
                default -> System.out.println("Invalid option");
            }
        }
    }

    private String selectSauce() {
        while (true) {
            System.out.printf(
                    "%n1. Mayo%n" +
                            "2. Mustard%n" +
                            "3. Ketchup%n" +
                            "4. Ranch%n" +
                            "5. Thousand Islands%n" +
                            "6. Vinaigrette%n" +
                            "0. Exit%n" +
                            "Toppings: "
            );

            switch (scanner.nextLine()) {
                case "1" -> { return "mayo"; }
                case "2" -> { return "mustard"; }
                case "3" -> { return "ketchup"; }
                case "4" -> { return "Ranch"; }
                case "5" -> { return "thousand_islands"; }
                case "6" -> { return "vinaigrette"; }
                case "0" -> { return "exit"; }
                default -> System.out.println("Invalid option");
            }
        }
    }

    private String selectSide() {
        while (true) {
            System.out.printf(
                    "%n1. Au jus%n" +
                            "2. Sauce%n" +
                            "0. Exit%n" +
                            "Toppings: "
            );

            switch (scanner.nextLine()) {
                case "1":
                    return "au_jus";
                case "2":
                    String sauce = selectSauce();

                    if (sauce.equals("exit")) {
                        continue;
                    }

                    return "side (" + sauce + ")";
                case "0":
                    return "exit";
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private void removeTopping(List<Topping> toppings) {
        if (toppings.isEmpty()) {
            System.out.printf("%nNo toppings to remove%n");
            return;
        }

        System.out.printf("%nToppings:%n");
        for (int i = 0; i < toppings.size(); i++) {
            System.out.println((i + 1) + ". " + toppings.get(i));
        }

        System.out.printf(
                "%n0. Exit%n" +
                        "Choose topping to remove: ");

        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;

            while (true) {
                if (index >= 0 && index < toppings.size()) {
                    toppings.remove(index);
                    System.out.println("Topping removed");
                    return;
                } else if (index == -1) {
                    System.out.println("Exiting...");
                    return;
                } else {
                    System.out.println("Invalid selection");
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
        }
    }
}