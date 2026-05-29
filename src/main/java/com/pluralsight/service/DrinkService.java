package com.pluralsight.service;

import com.pluralsight.model.Drink;

public class DrinkService {
    public Drink createDrink(String size, String flavor) {
        return new Drink(size, flavor);
    }
}