package com.pluralsight.models;

public class Topping {
    private String name;
    private String type;
    private boolean extra;
    private int sandwichSize;

    public Topping(String name, String type, boolean extra, int sandwichSize) {
        this.name = name;
        this.type = type;
        this.extra = extra;
        this.sandwichSize = sandwichSize;
    }

    public double getPrice() {
        double price = 0;

        if (type.equalsIgnoreCase("meat")) {
            switch (sandwichSize) {
                case 4:
                    price = 1.00;
                    break;
                case 8:
                    price = 2.00;
                    break;
                case 12:
                    price = 3.00;
                    break;
            }

            if (extra) {
                switch (sandwichSize) {
                    case 4:
                        price += 0.50;
                        break;
                    case 8:
                        price += 1.00;
                        break;
                    case 12:
                        price += 1.50;
                        break;
                }
            }
        }

        else if (type.equalsIgnoreCase("cheese")) {

            switch (sandwichSize) {
                case 4:
                    price = .75;
                    break;
                case 8:
                    price = 1.50;
                    break;
                case 12:
                    price = 2.25;
                    break;
            }

            if (extra) {
                switch (sandwichSize) {
                    case 4:
                        price += .30;
                        break;
                    case 8:
                        price += .60;
                        break;
                    case 12:
                        price += .90;
                        break;
                }
            }
        }

        return price;
    }

    @Override
    public String toString() {
        return name + (extra ? " (extra)" : "");
    }
}
