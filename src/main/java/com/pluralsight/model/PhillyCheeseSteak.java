package com.pluralsight.model;

import com.pluralsight.model.Sandwich;
import com.pluralsight.model.Topping;

import java.util.ArrayList;
import java.util.List;

public class PhillyCheeseSteak implements SandwichBuilder {

    @Override
    public Sandwich build() {
        List<Topping> toppings = new ArrayList<>();
        toppings.add(new Topping("steak", "meat", false, 8));
        toppings.add(new Topping("american", "cheese", false, 8));
        toppings.add(new Topping("peppers", "regular", false, 8));
        toppings.add(new Topping("mayo", "sauce", false, 8));

        return new Sandwich(8, "white", true, new ArrayList<>(toppings));
    }
}