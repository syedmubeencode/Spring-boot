package com.receipt.demo.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import lombok.*;

@Data
@Entity
@Table(name = "transaction_items")
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class LineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient // We don't usually put the DB primary key in the POSLOG
    private Long id;

    private String itemId;
    private String description;
    private Integer quantity;
    private Double price;
}