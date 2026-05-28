package com.pluralsight.util;

import com.pluralsight.model.Order;

import java.io.FileWriter;
import java.io.IOException;

public class ReceiptWriter {
    public void writeReceipt(Order order) {
        try {
            FileWriter writer = new FileWriter("receipt.txt");

            writer.write(order.toString());
            writer.write("\nTOTAL: $" + order.calculateTotal());

            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing receipt.txt file." + e.getMessage());
        }
    }
}
