package com.etiya.rentACarSpring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private int invoiceId;

    @Column(name = "invoice_no")
    private String invoiceNo;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "total_price")
    private double totalPrice;

    @OneToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;
}
