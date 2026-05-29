package com.pluralsight.model;

import java.util.ArrayList;
import java.util.List;

public class BLTSandwich implements SandwichBuilder {

    @Override
    public Sandwich build() {
        List<Topping> toppings = new ArrayList<>();
        toppings.add(new Topping("bacon", "meat", false, 8));
        toppings.add(new Topping("cheddar", "cheese", false, 8));
        toppings.add(new Topping("lettuce", "regular", false, 8));
        toppings.add(new Topping("tomato", "regular", false, 8));
        toppings.add(new Topping("ranch", "sauce", false, 8));

        return new Sandwich(8, "white", true, new ArrayList<>(toppings));
    }
}