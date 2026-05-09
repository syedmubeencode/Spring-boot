package com.receipt.demo.service;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.receipt.demo.model.LineItem;
import com.receipt.demo.model.Product;
import com.receipt.demo.model.Transaction;
import com.receipt.demo.repository.ProductRepository;
import com.receipt.demo.repository.TransactionRepository;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

@Service
public class CartService {

    @Autowired private TransactionRepository transactionRepo;
    @Autowired private ProductRepository productRepo;

    public Transaction createNewTransaction() {
        Transaction txn = new Transaction();
        txn.setTransactionId("TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        txn.setStoreId("HYD-TCS-01");
        txn.setStatus("OPEN");
        txn.setItems(new ArrayList<>());
        txn.setTotalAmount(0.0);
        return transactionRepo.save(txn);
    }

    public Product findProduct(String id) {
        return productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Transaction updateBasket(String txnId, LineItem itemRequest) {
        Transaction txn = transactionRepo.findById(txnId).get();
        Product product = findProduct(itemRequest.getItemId());

        // Create new LineItem from DB Product data
        LineItem newItem = new LineItem();
        newItem.setItemId(product.getItemId());
        newItem.setDescription(product.getDescription());
        newItem.setPrice(product.getPrice());
        newItem.setQuantity(itemRequest.getQuantity());

        txn.getItems().add(newItem);
        
        // Recalculate Total
        double total = txn.getItems().stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();
        txn.setTotalAmount(total);

        return transactionRepo.save(txn);
    }

    public Transaction finalizeTransaction(String txnId) {
        Transaction txn = transactionRepo.findById(txnId).get();
        txn.setStatus("PAID");
        
        // Generate and set the POSLOG XML before saving
        String xml = convertToPosLog(txn);
        txn.setPoslogXml(xml); 
        
        return transactionRepo.save(txn);
    }

    public String convertToPosLog(Transaction txn) {
        try {
            JAXBContext context = JAXBContext.newInstance(Transaction.class);
            Marshaller mar = context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            mar.marshal(txn, sw);
            return sw.toString();
        } catch (Exception e) {
            return "";
        }
    }
}