package com.pluralsight.model;

import java.util.ArrayList;
import java.util.List;

public class Sandwich {
    private int size;
    private String breadType;
    private boolean toasted;
    private ArrayList<Topping> toppings;

    public Sandwich(int size, String breadType, boolean toasted, ArrayList<Topping> toppings) {
        this.size = size;
        this.breadType = breadType;
        this.toasted = toasted;
        this.toppings = toppings;
    }

    public Sandwich(int size, String breadType, boolean toasted) {
        this.size = size;
        this.breadType = breadType;
        this.toasted = toasted;
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public double getPrice() {
        double price = 0;

        switch (size) {
            case 4:
                price += 5.50;
                break;
            case 8:
                price += 7.00;
                break;
            case 12:
                price += 8.50;
                break;
        }

        for (Topping topping : toppings) {
            price += topping.getPrice();
        }

        return price;
    }

    public int getSize() {
        return size;
    }

    public String getBreadType() {
        return breadType;
    }

    public boolean isToasted() {
        return toasted;
    }

    public List<Topping> getToppings() {
        return new ArrayList<>(toppings);
    }

    @Override
    public String toString() {

        StringBuilder output = new StringBuilder();

        output.append(size)
                .append("\" ")
                .append(breadType)
                .append(" sandwich\n");

        output.append("Toasted: ")
                .append(toasted ? "Yes" : "No")
                .append("\n");

        output.append("Toppings:\n");

        for (Topping topping : toppings) {
            output.append("- ")
                    .append(topping)
                    .append("\n");
        }

        output.append(String.format("Subtotal: $%.2f", getPrice()));

        return output.toString();
    }
}