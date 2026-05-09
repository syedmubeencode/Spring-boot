package com.receipt.demo.model;

import java.util.List;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@Entity
@Table(name = "transactions")
@XmlRootElement(name = "POSLog")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transaction {

    @Id
    private String transactionId;
    
    private String storeId;
    
    private String timestamp;
    
    private Double totalAmount;
    
    // This was missing - needed for txn.setStatus()
    private String status;

    // This was missing - needed for txn.setPoslogXml()
    @Column(columnDefinition = "TEXT")
    @XmlTransient // We don't want the XML string to be inside the XML itself
    private String poslogXml;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "transaction_id")
    @XmlElementWrapper(name = "Items")
    @XmlElement(name = "Item")
    private List<LineItem> items;
}