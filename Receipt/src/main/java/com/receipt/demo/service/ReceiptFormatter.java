package com.receipt.demo.service;

import com.receipt.demo.model.ReceiptLine;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReceiptFormatter {

    /**
     * Converts raw text with tags into a List of ReceiptLine objects.
     */
    public List<ReceiptLine> format(String rawOutput) {
        List<ReceiptLine> formattedLines = new ArrayList<>();
        String[] lines = rawOutput.split("\\r?\\n");

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;

            // Using the Builder pattern for clean object creation
            ReceiptLine rLine = ReceiptLine.builder()
                .text(line.replaceAll("<[^>]*>", "").trim()) // Remove tags for the raw text
                .alignment(line.contains("<center>") ? "CENTER" : (line.contains("<right>") ? "RIGHT" : "LEFT"))
                .style(line.contains("<bold>") ? "BOLD" : (line.contains("<italic>") ? "ITALIC" : "NORMAL"))
                .fontSize(line.contains("<double_height>") || line.contains("<double_width>") ? "2X" : "1X")
                .isBarcode(line.contains("<barcode>"))
                .isQrCode(line.contains("<qrcode>"))
                .build();

            formattedLines.add(rLine);
        }
        return formattedLines;
    }
}