package com.receipt.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptLine {
    private String text;
    private String alignment;
    private String style;
    private String fontSize;
    private boolean isBarcode;
    private boolean isQrCode;
}