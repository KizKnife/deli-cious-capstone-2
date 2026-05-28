package com.pluralsight.model;

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
            price = switch (sandwichSize) {
                case 4 -> 1.00;
                case 8 -> 2.00;
                case 12 -> 3.00;
                default -> price;
            };

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

            price = switch (sandwichSize) {
                case 4 -> .75;
                case 8 -> 1.50;
                case 12 -> 2.25;
                default -> price;
            };

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
