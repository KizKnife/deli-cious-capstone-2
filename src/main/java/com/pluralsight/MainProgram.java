package com.pluralsight;

import com.pluralsight.ui.UserInterface;

public class MainProgram {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();

        System.out.println("Welcome to the DELI-cious!");

        userInterface.display();
    }
}
