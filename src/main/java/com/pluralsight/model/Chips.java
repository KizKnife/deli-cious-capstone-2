package com.pluralsight.model;

public class Chips {
    private String chipFlavor;
    private static final double price = 1.50;

    public Chips(String chipFlavor) {
        this.chipFlavor = chipFlavor;
    }

    public String getChipFlavor() {
        return chipFlavor;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return chipFlavor
                + " chips - $"
                + String.format("%.2f", price);
    }
}