package com.pluralsight.model;


import java.util.HashMap;
import java.util.Map;

public class SpecialtySandwichRegistry {

    private final Map<String, SandwichBuilder> registry = new HashMap<>();

    public SpecialtySandwichRegistry() {
        registry.put("1", new BLTSandwich());
        registry.put("2", new PhillyCheeseSteak());
    }

    public SandwichBuilder get(String key) {
        return registry.get(key);
    }
}