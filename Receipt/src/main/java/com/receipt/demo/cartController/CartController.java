package com.receipt.demo.cartController;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.receipt.demo.model.LineItem;
import com.receipt.demo.model.Product;
import com.receipt.demo.model.ReceiptLine;
import com.receipt.demo.model.Transaction;
import com.receipt.demo.service.CartService;
import com.receipt.demo.service.PdfGeneratorService;
import com.receipt.demo.service.ReceiptEngineService;
import com.receipt.demo.service.ReceiptFormatter;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired private CartService cartService;
    @Autowired private ReceiptEngineService engine;
    @Autowired private PdfGeneratorService pdfService;

    @PostMapping("/begin")
    public Transaction beginTransaction() {
        return cartService.createNewTransaction();
    }

    @GetMapping("/lookup/{id}")
    public Product lookupItem(@PathVariable String id) {
        return cartService.findProduct(id);
    }

    @PostMapping("/{txnId}/add")
    public Transaction addItem(@PathVariable String txnId, @RequestBody LineItem item) {
        return cartService.updateBasket(txnId, item);
    }

    @PostMapping("/{txnId}/pay")
    public ResponseEntity<byte[]> processPaymentAndPrint(@PathVariable String txnId) {
        // 1. Finalize Payment in DB
        Transaction txn = cartService.finalizeTransaction(txnId);
        
        // 2. Generate POSLOG XML & Receipt
        String rawReceipt = engine.generateReceipt(txn, "receipt_v1.vm");
        List<ReceiptLine> lines = new ReceiptFormatter().format(rawReceipt);
        
        // 3. Generate PDF
        ByteArrayInputStream bis = pdfService.generatePdfReceipt(lines);
        
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=receipt.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(bis.readAllBytes());
    }
}