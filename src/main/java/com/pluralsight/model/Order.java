package com.pluralsight.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Sandwich> sandwiches;
    private List<Drink> drinks;
    private List<Chips> chips;

    public Order() {
        this.sandwiches = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.chips = new ArrayList<>();
    }

    public Order(List<Sandwich> sandwiches, List<Drink> drinks, List<Chips> chips) {
        this.sandwiches = sandwiches;
        this.drinks = drinks;
        this.chips = chips;
    }

    public void addSandwich (Sandwich sandwich) {
        sandwiches.add(sandwich);
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public void addChip(Chips chip) {
        chips.add(chip);
    }

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
