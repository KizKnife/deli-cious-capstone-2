package com.pluralsight.service;

import com.pluralsight.model.Chips;

public class ChipsService {
    public Chips createChips(String type) {
        return new Chips(type);
    }
}