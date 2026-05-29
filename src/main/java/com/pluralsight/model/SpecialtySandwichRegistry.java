package com.pluralsight.model;


import java.util.HashMap;
import java.util.Map;

public class SpecialtySandwichRegistry {
    // Stores mapping between user menu options and SandwichBuilder implementations for specialty sandwiches
    private final Map<String, SandwichBuilder> registry = new HashMap<>();

    // Initializes registry with available specialty sandwich builders
    public SpecialtySandwichRegistry() {
        registry.put("1", new BLTSandwich());
        registry.put("2", new PhillyCheeseSteak());
    }

    // Retrieves and returns SandwichBuilder based on the provided selection key
    // Returns null if no match is found
    public SandwichBuilder get(String key) {
        return registry.get(key);
    }
}