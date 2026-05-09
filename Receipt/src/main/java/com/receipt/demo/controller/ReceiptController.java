package com.receipt.demo.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.receipt.demo.model.ReceiptLine;
import com.receipt.demo.model.Transaction;
import com.receipt.demo.service.PdfGeneratorService;
import com.receipt.demo.service.ReceiptEngineService;
import com.receipt.demo.service.ReceiptFormatter;

@RestController
@RequestMapping("/api/receipt")
public class ReceiptController {
	
	@Autowired 
	private PdfGeneratorService pdfService;

    @Autowired 
    private ReceiptEngineService engine;
    
    @Autowired 
    private ReceiptFormatter formatter;
    
    @PostMapping(value = "/generate/pdf", produces = org.springframework.http.MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<org.springframework.core.io.InputStreamResource> createPdfReceipt(@RequestBody Transaction transaction) {
	    
	    // 1. Reuse existing logic to get the formatted lines
	    String rawReceipt = engine.generateReceipt(transaction, "receipt_v1.vm");
	    List<ReceiptLine> finalReceipt = formatter.format(rawReceipt);

	    // 2. Convert those lines into a PDF
	    ByteArrayInputStream bis = pdfService.generatePdfReceipt(finalReceipt);

	    // 3. Return as a downloadable/viewable PDF file
	    org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
	    headers.add("Content-Disposition", "inline; filename=receipt.pdf");

	    return ResponseEntity
	            .ok()
	            .headers(headers)
	            .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
	            .body(new org.springframework.core.io.InputStreamResource(bis));
	}
    

    @PostMapping("/generate")
    public ResponseEntity<List<ReceiptLine>> createReceipt(@RequestBody Transaction transaction) {
        // Step 1: Generate Raw Text via Velocity (The logic layer)
        // Ensure "receipt_v1.vm" exists in src/main/resources/templates/
        String rawReceipt = engine.generateReceipt(transaction, "receipt_v1.vm");
        
        // Step 2: Format into Printer-Friendly JSON (The rendering layer)
        List<ReceiptLine> finalReceipt = formatter.format(rawReceipt);
        
        return ResponseEntity.ok(finalReceipt);
    }
}