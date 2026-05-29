package com.pluralsight.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Sandwich> sandwiches;
    private List<Drink> drinks;
    private List<Chips> chips;

    // Initializes new empty order with separate lists for sandwiches, drinks, and chips
    public Order() {
        this.sandwiches = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.chips = new ArrayList<>();
    }

    // Creates order using pre-existing lists of sandwiches, drinks, and chips
    public Order(List<Sandwich> sandwiches, List<Drink> drinks, List<Chips> chips) {
        this.sandwiches = sandwiches;
        this.drinks = drinks;
        this.chips = chips;
    }

    // Adds sandwich to the current order
    public void addSandwich (Sandwich sandwich) {
        sandwiches.add(sandwich);
    }

    // Adds drink to the current order
    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    // Adds chip item to the current order
    public void addChip(Chips chip) {
        chips.add(chip);
    }

    // Calculates and returns total cost of all items in the order by summing sandwiches, drinks, and chips
    public double calculateTotal() {
        double total = 0;

        for (Sandwich sandwich : sandwiches) {
            total += sandwich.getPrice();
        }

        for (Drink drink : drinks) {
            total += drink.getPrice();
        }

        for (Chips chips : chips) {
            total += chips.getPrice();
        }

        return total;
    }

    // Returns list of sandwiches in current order
    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    // Returns list of drinks in current order
    public List<Drink> getDrinks() {
        return drinks;
    }

    // Returns list of chips in current order
    public List<Chips> getChips() {
        return chips;
    }

    // Builds and returns formatted string of entire order
    // Includes all items grouped together for checkout display
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        output.append("===== CHECKOUT =====");

        boolean hasItems = false;

        for (Sandwich sandwich : getSandwiches()) {
            if (!hasItems) {
                output.append("\n\n");
                hasItems = true;
            } else {
                output.append("\n");
            }

            output.append(sandwich);
        }

        for (Drink drink : getDrinks()) {
            if (!hasItems) {
                output.append("\n\n");
                hasItems = true;
            } else {
                output.append("\n\n");
            }

            output.append(drink);
        }

        for (Chips chip : getChips()) {
            if (!hasItems) {
                output.append("\n\n");
                hasItems = true;
            } else {
                output.append("\n\n");
            }

            output.append(chip);
        }

        return output.toString();
    }
}