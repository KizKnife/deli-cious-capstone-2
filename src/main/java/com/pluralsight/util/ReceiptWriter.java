package com.pluralsight.util;

import com.pluralsight.model.Order;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptWriter {
    public void writeReceipt(Order order) {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

        String fileName = now.format(formatter) + ".txt";

        try (FileWriter writer = new FileWriter(fileName)) {

            writer.write("Date: " + now.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + "\n");
            writer.write("Time: " + now.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n\n");
            writer.write(order.toString());
            writer.write("\nTOTAL: $" + order.calculateTotal());

        } catch (IOException e) {
            System.out.println("Error writing receipt file: " + e.getMessage());
        }
    }
}