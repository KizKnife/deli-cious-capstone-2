package com.pluralsight.service;

import com.pluralsight.model.Sandwich;
import com.pluralsight.model.Topping;

import java.util.ArrayList;
import java.util.List;

public class SandwichService {
    public Sandwich createSandwich(int size, String breadType, boolean toasted, List<Topping> toppings) {
        return new Sandwich(size, breadType, toasted, new ArrayList<>(toppings));
    }
}